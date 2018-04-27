package com.ytzl.itrip.auth.service;

import com.ytzl.itrip.utils.exception.ItripExcepion;

/**
 * 短信接口
 * Created by Administrator on 2018/4/24 0024.
 */
public interface MessageService {
    /**
     * 发送方法
     * @param to 目标
     * @param templateId 模板对象
     * @param datas 发送的数据
     */
    public void send(String to, String templateId, String[] datas) throws ItripExcepion;
}
