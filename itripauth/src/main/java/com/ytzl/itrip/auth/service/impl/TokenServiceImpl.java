package com.ytzl.itrip.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.ytzl.itrip.auth.service.TokenService;
import com.ytzl.itrip.beans.model.ItripUser;
import com.ytzl.itrip.utils.common.*;
import com.ytzl.itrip.utils.exception.ItripExcepion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ValidationUtil validationUtil;

    /**
     * 创建token
     * @param userAgent
     * @param itripUser
     * @return
     */
    @Override
    public String generateToken(String userAgent, ItripUser itripUser) {
        StringBuffer sbToken = new StringBuffer("token:");
        // 拼接客户端标识
        if (UserAgentUtil.CheckAgent(userAgent))
            sbToken.append("MOBILE-");
        else
            sbToken.append("PC-");
        // userCode
        String md5UserCode = DigestUtil.hmacSign(itripUser.getUserCode());
        sbToken.append(md5UserCode + "-");
        // userId
        sbToken.append(itripUser.getId() + "-");
        // createTime
        sbToken.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-");
        // 六位的userAgent加密码
        String md5UserAgent = DigestUtil.hmacSign(userAgent, 6);
        sbToken.append(md5UserAgent);
        return sbToken.toString();
    }

    @Override
    public void saveToken(String token, ItripUser itripUser) {
        String itripUserJson = JSON.toJSONString(itripUser);
        if (token.indexOf("PC-") != -1) {
            // pc端设置保存时间2小时
            redisUtil.set(token, itripUserJson, 60 * 60 * 2);
        } else {
            // 移动端不设置保存时间
            redisUtil.set(token, itripUserJson);
        }
    }

    /**
     * token验证功能
     *      作用：验证token是否有效
     * @param token 传入的token
     * @param userAgent 传入的客户端标识
     * @return
     */
    @Override
    public Boolean validateToken(String token, String userAgent) {
        // 判断token是否存在
        if (!redisUtil.exists(token))
            return false;
        // 两次浏览器userAgent完全一致，视为有效
        String tokenUserAgent = token.split("-")[4];
        if (!DigestUtil.hmacSign(userAgent, 6).equals(tokenUserAgent))
            return false;
        return true;
    }

    @Override
    public void removeToken(String token) {
        if (redisUtil.exists(token))
            redisUtil.del(token);
    }

    @Override
    public String retoken(String userAgent, String token) throws ItripExcepion {
        // 判断token是否有效
        if (!validateToken(token, userAgent))
            throw new ItripExcepion("未知token或token已过期", ErrorCode.AUTH_REPLACEMENT_FAILED);
        // 2、是否可以被置换，是否处于保护期内
        // 时间 1小时
        // 当前时间-token构建时间 > 大于保护期 可以置换
        try {
            // 构建时间
            long genTime = new SimpleDateFormat("yyyyMMddHHmmss")
                    .parse(token.split("-")[3]).getTime();
            long currTime = new Date().getTime(); // 当前时间
            if (currTime - genTime < TOKEN_ROTECTION_PERIOD) {
                throw new ItripExcepion("token正处于保护期内，禁止替换"
                        , ErrorCode.AUTH_REPLACEMENT_FAILED);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long ttl = redisUtil.ttl(token); // 获取剩余时间
        if(ttl > 0 || ttl == -1) {

            // 获取用户对象
            ItripUser itripUser = validationUtil.getUser(token);
            // 进行替换 生成新的token
            String newToken = generateToken(userAgent, itripUser);
            // 老的token延迟过期
            redisUtil.set(token, JSON.toJSONString(itripUser), 60*3);
            // 新的token保存到redis中
            saveToken(newToken, itripUser);
            return newToken;
        } else {
            throw new ItripExcepion("未知token或token已过期", ErrorCode.AUTH_REPLACEMENT_FAILED);
        }
    }
}