package com.github.joine.restapi.controller.business;

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
    public String test() {
        return "HelloWorld";
    }
}
