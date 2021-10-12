package com.ztqees.controller;

import com.alibaba.fastjson.JSONObject;
import com.ztqees.entity.TblUserRecord;
import com.ztqees.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouqien
 * @date 2021-10-10 22:08
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/auth/2step-code")
    public boolean step_code2(){
        System.out.println("此请求是前端框架带的默认请求，可以不做任何处理，也可以在前端将其删除");
        System.out.println("step_code2");
        return true;
    }

    @RequestMapping("/auth/login")
    public JSONObject login(String username,String password){
        System.out.println("login");
        TblUserRecord tblUserRecord = loginService.login(username, password);
        System.out.println(tblUserRecord);
        return JSONObject.parseObject(JSONObject.toJSONString(tblUserRecord));
    }
}
