package com.ytzl.itrip.auth.service.impl;

import com.ytzl.itrip.auth.service.MailService;
import com.ytzl.itrip.utils.exception.ItripExcepion;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/25 0025.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSenderImpl javaMailSender;
    @Resource
    private SimpleMailMessage simpleMailMessage;
    @Override
    public void send(String to, String code) throws ItripExcepion {
        // 发送给某个对象
        simpleMailMessage.setTo(to);
        // 邮件内容
        simpleMailMessage.setText("您好，您的邮箱激活码为："
                + code + ",请在三分钟内完成邮箱激活");
        // 发送邮件
        javaMailSender.send(simpleMailMessage);
    }
}
