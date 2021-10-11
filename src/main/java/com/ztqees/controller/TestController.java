package com.ztqees.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhouqien
 * @date 2021-10-10 16:21
 */
//@Controller
public class TestController {
    @RequestMapping("/auth/login")
    @ResponseBody
    public String test(){
        System.out.println("success ztqees");
        return "hello ztqees";
    }
}
