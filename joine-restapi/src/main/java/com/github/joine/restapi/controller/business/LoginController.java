package com.github.joine.restapi.controller.business;

import com.github.joine.business.domain.User;
import com.github.joine.common.core.controller.BaseController;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.restapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/22 4:10 PM
 */
@RestController
@RequestMapping("/auth")
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User loginUser) {
        String token = loginService.login(loginUser);
        return ResponseResult.successResponse(token);
    }

    @GetMapping("/getUserId")
    public ResponseResult getUserId() {
        return ResponseResult.success(getCurrentUserId());
    }
}
