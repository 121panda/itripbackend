package com.ytzl.itrip.auth.service.impl;

import com.ytzl.itrip.auth.service.ItripUserService;
import com.ytzl.itrip.auth.service.MailService;
import com.ytzl.itrip.auth.service.MessageService;
import com.ytzl.itrip.dao.mapper.ItripUserMapper;
import com.ytzl.itrip.beans.model.ItripUser;
import com.ytzl.itrip.utils.common.*;
import com.ytzl.itrip.utils.exception.ItripExcepion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("itripUserService")
public class ItripUserServiceImpl implements ItripUserService {

    @Resource
    private ItripUserMapper itripUserMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private MessageService messageService;
    @Resource
    private MailService mailService;
    public ItripUser getItripUserById(Long id) throws Exception {
        return itripUserMapper.getItripUserById(id);
    }

    public List<ItripUser> getItripUserListByMap(Map<String, Object> param) throws Exception {
        return itripUserMapper.getItripUserListByMap(param);
    }

    public Integer getItripUserCountByMap(Map<String, Object> param) throws Exception {
        return itripUserMapper.getItripUserCountByMap(param);
    }

    public Integer saveItripUser(ItripUser itripUser) throws Exception {
        itripUser.setCreationDate(new Date());
        return itripUserMapper.saveItripUser(itripUser);
    }

    public Integer modifyItripUser(ItripUser itripUser) throws Exception {
        itripUser.setModifyDate(new Date());
        return itripUserMapper.modifyItripUser(itripUser);
    }

    public Integer removeItripUserById(Long id) throws Exception {
        return itripUserMapper.removeItripUserById(id);
    }

    public Page<ItripUser> queryItripUserPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripUserMapper.getItripUserCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripUser> itripUserList = itripUserMapper.getItripUserListByMap(param);
        page.setRows(itripUserList);
        return page;
    }

    /**
     * 查询用户根据用户code
     * 如果用户code登录null，用户不存在
     * 如果用户存在，判断用户密码是否正确  （在这之前要对密码进行加密操作）
     * 如果密码不一致，登录失败，用户名或密码错误
     * 如果密码一致，判断用户是否被激活
     * 如果用户以激活登录成功返回itripUser
     * 否则用户为被激活，返回错误信息
     *
     * @param userCode
     * @param userPassword
     * @return
     * @throws ItripExcepion
     */
    @Override
    public ItripUser login(String userCode, String userPassword) throws ItripExcepion {
        ItripUser itripUser = getItripUserByUserCode(userCode);
        if (null != itripUser) {
            /**
             * 用户名存在，进行密码判断
             * 1、加密密码
             */
            String md5UserPassword = DigestUtil.hmacSign(userPassword);
            if (itripUser.getUserPassword().equals(md5UserPassword)) {
                // 判断用户是否被激活 1 被激活，0未激活
                if (itripUser.getActivated() == 1) {
                    return itripUser;
                } else {
                    // 未被激活，返回用户未激活
                    throw new ItripExcepion("用户未激活", ErrorCode.AUTH_AUTHENTICATION_FAILED);
                }
            } else {
                throw new ItripExcepion("用户名密码不存在", ErrorCode.AUTH_AUTHENTICATION_FAILED);
            }
        } else {
            throw new ItripExcepion("用户不存在", ErrorCode.AUTH_AUTHENTICATION_FAILED);
        }
    }

    /**
     * 通过用户码获取用户
     *
     * @param userCode
     * @return
     * @throws ItripExcepion
     */
    @Override
    public ItripUser getItripUserByUserCode(String userCode) throws ItripExcepion {
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", userCode);
        try {
            List<ItripUser> userList = getItripUserListByMap(param);
            return userList != null && userList.size() > 0 ? userList.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void registerByPhone(ItripUser itripUser) throws ItripExcepion {
        /**
         * 添加用户到数据库
         * 生成短信验证码
         * 发送短信验证码
         * 保存短信验证码，时间为3分钟
         */

        try {
            // 未激活
            itripUser.setActivated(0);
            // 添加用户到数据库
            saveItripUser(itripUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ItripExcepion("注册用户异常,请稍后在试", ErrorCode.AUTH_UNKNOWN);
        }
        // 生成短信验证码
        int code = DigestUtil.randomCode();
        // 向用户发送短信验证
        messageService.send(itripUser.getUserCode(),"1", new String[] {""+code, "三"});
        // 保存短信验证码，时间为3分钟
        redisUtil.set("activation:" + itripUser.getUserCode(), "" + code, 60 * 3);
    }

    @Override
    public void activatePhone(String userCode, String code) throws ItripExcepion {
        // 获取redis中的验证码
        String rCode = redisUtil.get("activation:" + userCode);
        // 判断和用户输入是否一致
        if (rCode != null && rCode.equals(code)) {
            // 通过账号查询用户对象
            ItripUser itripUser = getItripUserByUserCode(userCode);
            // 以激活
            itripUser.setActivated(1);
            itripUser.setFlatID(itripUser.getId());
            itripUser.setUserType(0);
            try {
                // 一致则更新用户信息
                modifyItripUser(itripUser);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItripExcepion("激活异常,请稍后再试", ErrorCode.AUTH_UNKNOWN);
            }
        } else {
            // 否则通知验证码错误
            throw new ItripExcepion("验证失败");
        }


    }

    @Override
    public void doregister(ItripUser itripUser) throws ItripExcepion {
        try {
            // 未激活
            itripUser.setActivated(0);
            // 添加用户到数据库
            saveItripUser(itripUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ItripExcepion("注册用户异常,请稍后在试", ErrorCode.AUTH_UNKNOWN);
        }
        // 生成邮箱激活码
        String code = DigestUtil.hmacSign(System.currentTimeMillis()+ "",
                UUID.randomUUID() + "");
        // 向用户发送邮箱激活码
        mailService.send(itripUser.getUserCode(),code);
        // 保存邮箱激活码，时间为3分钟
        redisUtil.set("activation:" + itripUser.getUserCode(), "" + code, 60 * 3);
    }

    @Override
    public void activate(String userCode, String code) throws ItripExcepion {
        activatePhone(userCode,code);
    }
}
