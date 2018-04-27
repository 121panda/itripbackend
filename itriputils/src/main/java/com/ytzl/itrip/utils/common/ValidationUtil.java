package com.ytzl.itrip.utils.common;

import com.alibaba.fastjson.JSON;
import com.ytzl.itrip.beans.model.ItripUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
@Component
public class ValidationUtil {
    @Resource
    private RedisUtil redisUtil;
    /**
     *
     * 通过token获取用户信息
     *
     */
    public ItripUser getUser(String token) {
        //判断token是否存在
        try {
            if(!redisUtil.exists(token))
                return null;
            // 存在   获取token中的用户信息(json形式)
            String itripUserJson = redisUtil.get(token);
            // 将信息转化成itripUser对象
            ItripUser itripUser = JSON.parseObject(itripUserJson, ItripUser.class);
            // 返回用户对象
            return itripUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
