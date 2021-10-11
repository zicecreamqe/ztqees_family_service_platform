package com.ztqees.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouqien
 * @date 2021-10-10 22:08
 */
@RestController
public class LoginController {
    @RequestMapping("/auth/2step-code")
    public boolean step_code2(){
        System.out.println("此请求是前端框架带的默认请求，可以不做任何处理，也可以在前端将其删除");
        System.out.println("step_code2");
        return true;
    }

    @RequestMapping("/auth/login")
    public String login(String username,String password){
        System.out.println("login");
        System.out.println(username+"--"+password);
        return "success";
    }
}
