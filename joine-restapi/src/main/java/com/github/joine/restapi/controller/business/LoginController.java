package com.github.joine.restapi.controller.business;

import com.github.joine.business.domain.User;
import com.github.joine.business.service.IUserService;
import com.github.joine.common.core.controller.BaseController;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.common.exception.user.UserNotExistsException;
import com.github.joine.common.exception.user.UserPasswordNotMatchException;
import com.github.joine.restapi.annotation.PassAuth;
import com.github.joine.restapi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/22 4:10 PM
 */
@RestController
@RequestMapping("/auth")
public class LoginController extends BaseController {
    @Autowired
    private IUserService iUserService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User loginUser) {
        List<User> users = iUserService.selectUserList(loginUser);
        if (CollectionUtils.isEmpty(users)) {
            throw new UserNotExistsException();
        }
        User user = users.get(0);
        // TODO 加密
        if (user.getPassword().equals(loginUser.getPassword())) {
            return ResponseResult.successResponse(JWTUtil.sign(loginUser.getLoginName(), loginUser.getPassword()));
        } else {
            throw new UserPasswordNotMatchException();
        }
    }

    @GetMapping("/getUserId")
    public ResponseResult getUserId() {
        return ResponseResult.success(getCurrentUserId());
    }
}
