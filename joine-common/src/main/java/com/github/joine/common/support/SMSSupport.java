package com.github.joine.common.support;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.joine.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: JenphyJohn
 * @Date: 2019/9/24 3:04 PM
 */
public class SMSSupport {

    private static Logger logger = LoggerFactory.getLogger(SMSSupport.class);
    private static final String REGION = "cn-hangzhou";

    @Value("${aliyun.sms.accessKeyId:0}")
    private String accessKeyId;
    @Value("${aliyun.sms.accessKeySecret:0}")
    private String accessKeySecret;
    @Value("${aliyun.sms.signName:0}")
    private String signName;

    public void sendSMS(String mobile, String templateCode, String JSONParam) {

        // 初始化acsClient
        IClientProfile profile = DefaultProfile.getProfile(REGION, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 待发送手机号
        request.setPhoneNumbers(mobile);
        // 短信签名
        request.setSignName(signName);
        // 短信模板
        request.setTemplateCode(templateCode);
        // 模板中的变量替换JSON串
        if (StringUtils.isNotBlank(JSONParam)) {
            request.setTemplateParam(JSONParam);
        }
        try {
            SendSmsResponse sendSmsResponse = client.getAcsResponse(request);
            logger.info("短信发送完毕，手机号：{}，返回状态：{}", mobile, sendSmsResponse.getCode());
        } catch (ClientException e) {
            logger.error("短信发送异常", e);
        }

    }
}
