package com.ztqees.controller;

import com.alibaba.fastjson.JSONObject;
import com.ztqees.entity.TblUserRecord;
import com.ztqees.returnJson.Permission;
import com.ztqees.returnJson.Permissions;
import com.ztqees.returnJson.ReturnObject;
import com.ztqees.returnJson.UserInfo;
import com.ztqees.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public JSONObject login(String username, String password, HttpSession httpSession){
        System.out.println("login");
        TblUserRecord tblUserRecord = loginService.login(username, password);
        //将用户数据写到session中
        httpSession.setAttribute("tblUserRecord", tblUserRecord);
        System.out.println(tblUserRecord);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        ReturnObject returnObject =new ReturnObject(tblUserRecord);
        return JSONObject.parseObject(JSONObject.toJSONString(returnObject));
    }

    @RequestMapping("/user/info")
    public JSONObject getInfo(HttpSession httpSession){
        //获取用户数据（直接通过session得到就不用再连接数据库查询了）
        TblUserRecord userRecord = (TblUserRecord) httpSession.getAttribute("tblUserRecord");
        //获取对应用户需要账务的功能模块信息
        String[] rolePrivileges = userRecord.getTblRole().getRolePrivileges().split("-");
        // 拼接需要返回的数据对象的格式
        //向权限集合对象中添加具体的权限
        List<Permission> permissionList = new ArrayList<>();
        for (String rolePrivilege : rolePrivileges) {
            permissionList.add(new Permission(rolePrivilege));
        }
        //创建权限集合对象
        Permissions permissions =new Permissions();
        permissions.setPermissions(permissionList);
        //设置返回值的result
        UserInfo userInfo = new UserInfo(userRecord.getUserName(),permissions);
        ReturnObject returnObject = new ReturnObject(userInfo);
        return JSONObject.parseObject(JSONObject.toJSONString(returnObject));
        //return JSONObject.toJSONString(new ReturnObject(userInfo));
    }
}
