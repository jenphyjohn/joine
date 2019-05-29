package com.github.joine.restapi.controller.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.joine.common.constant.ResponseEnum;
import com.github.joine.common.core.controller.BaseController;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.restapi.annotation.PassAuth;
import com.github.joine.restapi.config.WxMaConfiguration;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JenphyJohn
 * @Date: 2019/5/28 4:49 PM
 */
@RestController
@RequestMapping("/{version}/apps/{appid}/auth")
public class WeChatController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PassAuth
    @GetMapping("/token")
    public ResponseResult login(@PathVariable String version, @PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return ResponseResult.response(ResponseEnum.REQUEST_FORMAT_NOT_SUPPORT);
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            logger.info(session.getSessionKey());
            logger.info(session.getOpenid());
            //TODO 增加业务逻辑，关联业务相关数据
            return ResponseResult.success();
        } catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.error(e.toString());
        }
    }

    /**
     * 获取用户信息接口
     *
     * @param appid
     * @param sessionKey
     * @param signature
     * @param rawData
     * @param encryptedData
     * @param iv
     * @return
     */
    @GetMapping("/info")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JSONUtils.toJSONString(userInfo);
    }

    /**
     * 获取用户绑定手机号信息
     *
     * @param appid
     * @param sessionKey
     * @param signature
     * @param rawData
     * @param encryptedData
     * @param iv
     * @return
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JSONUtils.toJSONString(phoneNoInfo);
    }

}
