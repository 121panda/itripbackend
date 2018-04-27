package com.ytzl.itrip.auth.service.impl;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.ytzl.itrip.auth.service.MessageService;
import com.ytzl.itrip.utils.common.ErrorCode;
import com.ytzl.itrip.utils.exception.ItripExcepion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

/**
 * 短信接口实现类
 * Created by Administrator on 2018/4/24 0024.
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Value("#{itrip.sms_serverIP}")
    private String serverIP;
    @Value("#{itrip.sms_serverSid}")
    private String serverSid;
    @Value("#{itrip.sms_accountPort}")
    private String accountPort;
    @Value("#{itrip.sms_acountToken}")
    private String acountToken;
    @Value("#{itrip.sms_appId}")
    private String appId;
    /**
     * 发送方法
     * @param to 目标
     * @param templateId 模板对象
     * @param datas 发送的数据
     */
    @Override
    public void send(String to, String templateId, String[] datas) throws ItripExcepion {
        // map集合保存结果
        HashMap<String, Object> result = null;
        // 获取api对象
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
        restAPI.init(serverIP, accountPort);
        // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
        restAPI.setAccount(serverSid, acountToken);
        // 请使用管理控制台中已创建应用的APPID。
        restAPI.setAppId(appId);
        // 获取返送结果   to 用户账号    templateId 用户id  datas
        result = restAPI.sendTemplateSMS(to,templateId ,datas);
        // 判断返回的状态码是否正确
        if("000000".equals(result.get("statusCode"))){
            // 正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返抛出错误码和错误信息
            throw new ItripExcepion("短信发送失败，请稍后再试", ErrorCode.AUTH_UNKNOWN);
        }
    }
}
