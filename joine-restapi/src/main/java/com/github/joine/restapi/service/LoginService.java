package com.github.joine.restapi.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.github.joine.business.domain.User;
import com.github.joine.business.service.IUserService;
import com.github.joine.common.exception.user.UserNotExistsException;
import com.github.joine.common.exception.user.UserPasswordNotMatchException;
import com.github.joine.common.utils.StringUtils;
import com.github.joine.common.utils.security.Md5Utils;
import com.github.joine.restapi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/26 3:56 PM
 */
@Service
public class LoginService {

    @Autowired
    private IUserService iUserService;

    public String login(User loginUser) {
        User user = iUserService.selectUserByLoginName(loginUser.getLoginName());
        if (user == null) {
            throw new UserNotExistsException();
        }
        if (user.getPassword().equals(Md5Utils.hash(loginUser.getPassword()))) {
            return JWTUtil.sign(loginUser.getLoginName(), loginUser.getPassword());
        } else {
            throw new UserPasswordNotMatchException();
        }
    }

    public String wechatLogin(WxMaJscode2SessionResult wxMaJscode2SessionResult, String ipAddr) {
        String openid = wxMaJscode2SessionResult.getOpenid();
        User user = iUserService.selectUserByOpenid(openid);
        if (user == null) {
            user = new User();
            user.setLoginName("wechat_user_" + StringUtils.getRandomString(12));
            user.setPassword(Md5Utils.hash(openid));
            user.setWxOpenid(openid);
            user.setWxUnionid(wxMaJscode2SessionResult.getUnionid());
            user.setLoginTime(new Date());
            user.setLoginIp(ipAddr);
            user.setRegisterTime(new Date());
            user.setRegisterIp(ipAddr);
            iUserService.insertUser(user);
        }
        return JWTUtil.signWeChat(wxMaJscode2SessionResult);
    }
}
