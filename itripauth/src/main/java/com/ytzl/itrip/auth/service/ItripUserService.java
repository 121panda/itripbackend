package com.ytzl.itrip.auth.service;
import com.ytzl.itrip.beans.model.ItripUser;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.common.Page;
import com.ytzl.itrip.utils.exception.ItripExcepion;

/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripUserService {

    public ItripUser getItripUserById(Long id)throws Exception;

    public List<ItripUser>	getItripUserListByMap(Map<String, Object> param)throws Exception;

    public Integer getItripUserCountByMap(Map<String, Object> param)throws Exception;

    public Integer saveItripUser(ItripUser itripUser)throws Exception;

    public Integer modifyItripUser(ItripUser itripUser)throws Exception;

    public Integer removeItripUserById(Long id)throws Exception;

    public Page<ItripUser> queryItripUserPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @return
     * @throws ItripExcepion
     */
    public ItripUser login(String userCode, String userPassword) throws ItripExcepion;

    public ItripUser getItripUserByUserCode(String userCode) throws ItripExcepion;

    /**
     * 根据手机号注册
     * @param itripUser
     * @throws ItripExcepion
     */
    public void registerByPhone(ItripUser itripUser) throws ItripExcepion;

    /**
     * 激活手机号
     * @param userCode
     * @param code
     * @throws ItripExcepion
     */
    public void activatePhone(String userCode, String code) throws ItripExcepion;

    /**
     * 邮箱注册
     * @param itripUser
     * @throws ItripExcepion
     */
    public void doregister(ItripUser itripUser) throws ItripExcepion;

    /**
     * 激活邮箱
     * @param userCode
     * @param code
     * @throws ItripExcepion
     */
    public void activate(String userCode, String code) throws  ItripExcepion;
}
