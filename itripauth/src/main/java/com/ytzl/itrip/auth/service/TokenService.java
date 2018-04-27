package com.ytzl.itrip.auth.service;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

import com.ytzl.itrip.beans.model.ItripUser;
import com.ytzl.itrip.utils.exception.ItripExcepion;

/**
 * token接口
 *      作用：创建、保存token
 */
public interface TokenService {
    // token有效时间(毫秒)
    public static final long TOKEN_ROTECTION_PERIOD = 60*500;
    /**
     * 构建token
     * @param userAgent
     * @param itripUser
     * @return
     */
    public String generateToken (String userAgent, ItripUser itripUser);

    /**
     * 保存token
     * @param token
     * @param itripUser
     */
    public void saveToken (String token, ItripUser itripUser);

    /**
     * 验证token是否有效（退出或注销是使用）
     * @param token
     * @param userAgent
     * @return
     */
    public Boolean validateToken(String token, String userAgent);

    /**
     * 删除token
     * @param token
     */
    public void removeToken(String token);

    /**
     * 重置token
     */
    public String retoken(String token, String userAgent) throws ItripExcepion;
}
