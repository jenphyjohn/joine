package com.github.joine.restapi.controller.business;

import com.github.joine.common.core.controller.BaseController;
import com.github.joine.common.core.domain.ResponseResult;
import com.github.joine.restapi.result.LoginResult;
import com.github.joine.restapi.service.LoginService;
import com.github.joine.system.domain.SysUser;
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
    public ResponseResult login(@RequestBody SysUser loginUser) {
        LoginResult loginResult = loginService.login(loginUser);
        return ResponseResult.successResponse(loginResult);
    }

    @GetMapping("/getUserId")
    public ResponseResult getUserId() {
        return ResponseResult.success(getCurrentUserId());
    }
}
