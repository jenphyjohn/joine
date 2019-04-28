package com.github.joine.restapi.service;

import com.github.joine.business.domain.User;
import com.github.joine.business.service.IUserService;
import com.github.joine.common.exception.user.UserNotExistsException;
import com.github.joine.common.exception.user.UserPasswordNotMatchException;
import com.github.joine.common.utils.security.Md5Utils;
import com.github.joine.restapi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/26 3:56 PM
 */
@Service
public class LoginService {

    @Autowired
    private IUserService iUserService;

    public String login(User loginUser) {
        User user = iUserService.selectUserByLoginName(loginUser);
        if (user == null) {
            throw new UserNotExistsException();
        }
        if (user.getPassword().equals(Md5Utils.hash(loginUser.getPassword()))) {
            return JWTUtil.sign(loginUser.getLoginName(), loginUser.getPassword());
        } else {
            throw new UserPasswordNotMatchException();
        }
    }
}
