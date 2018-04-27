package com.ytzl.itrip.auth.service;

import com.ytzl.itrip.utils.exception.ItripExcepion;

/**
 * Created by Administrator on 2018/4/25 0025.
 */
public interface MailService {
    public void send(String to, String code) throws ItripExcepion;
}
