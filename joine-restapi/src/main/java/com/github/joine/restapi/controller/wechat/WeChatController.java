package com.github.joine.restapi.controller.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.joine.common.constant.ResponseEnum;
import com.github.joine.common.core.controller.BaseController;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.common.exception.user.UserTokenExpiredException;
import com.github.joine.common.utils.IpUtils;
import com.github.joine.restapi.annotation.PassAuth;
import com.github.joine.restapi.config.WxMaConfiguration;
import com.github.joine.restapi.domain.RawUserBean;
import com.github.joine.restapi.domain.TokenBean;
import com.github.joine.restapi.service.LoginService;
import com.github.joine.restapi.util.JWTUtil;
import com.github.joine.system.domain.SysUser;
import com.github.joine.system.service.ISysUserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * @Author: JenphyJohn
 * @Date: 2019/5/28 4:49 PM
 */
@RestController
@RequestMapping("/{version}/apps/{appid}/auth")
public class WeChatController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private ISysUserService iUserService;

    @PassAuth
    @GetMapping("/token")
    public ResponseResult login(@PathVariable String version, @PathVariable String appid, String code, HttpServletRequest request) {
        if (StringUtils.isBlank(code)) {
            return ResponseResult.response(ResponseEnum.REQUEST_FORMAT_NOT_SUPPORT);
        }

        String ipAddr = IpUtils.getIpAddr(request);

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            logger.info(session.getSessionKey());
            logger.info(session.getOpenid());
            String token = loginService.wechatLogin(session, ipAddr);
            ResponseResult result = ResponseResult.success(new TokenBean(token));
            return result;
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

    @PassAuth
    @PostMapping("/verify")
    public ResponseResult verify(@Valid @RequestBody TokenBean tokenBean) {
        String token = tokenBean.getToken();
        logger.info("token:{}", token);
        // 验证 token
        if (!JWTUtil.verify(token)) {
            throw new UserTokenExpiredException();
        }
        return ResponseResult.successResponse();
    }

    @GetMapping("/me")
    public ResponseResult me(@RequestAttribute String openid) {
        logger.info("openid:{}", openid);
        SysUser user = iUserService.selectUserByOpenid(openid);
        return ResponseResult.successResponse(user);
    }


    /**
     * 校验用户信息接口
     *
     * @param appid
     * @param sessionKey
     * @param rawUserBean
     * @return
     */
    @PostMapping("/decode")
    public ResponseResult decode(@PathVariable String appid, @RequestAttribute String sessionKey,
                                 @RequestAttribute String openid , @RequestBody RawUserBean rawUserBean,
                                 HttpServletRequest request) {
        String signature = rawUserBean.getSignature();
        String rawData = rawUserBean.getRawData();
        String encryptedData = rawUserBean.getEncryptedData();
        String iv = rawUserBean.getIv();
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return ResponseResult.response(ResponseEnum.WX_USERINFO_ERROR);
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        // 更新用户信息
        SysUser user = iUserService.selectUserByOpenid(openid);
        user.setAvatar(userInfo.getAvatarUrl());
        user.setSex(userInfo.getGender());
        user.setWxNickName(userInfo.getNickName());
        user.setLoginDate(new Date());
        user.setLoginIp(IpUtils.getIpAddr(request));
        iUserService.updateUser(user);

        return ResponseResult.successResponse(userInfo);
    }

}
