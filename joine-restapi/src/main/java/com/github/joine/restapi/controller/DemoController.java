package com.github.joine.restapi.controller;

import com.github.joine.restapi.annotation.PassAuth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/17 4:55 PM
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/test")
    @PassAuth
    public String test() {
        return "HelloWorld";
    }
}
