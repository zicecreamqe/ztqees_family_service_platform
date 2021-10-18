package com.ztqees.controller;

import com.alibaba.fastjson.JSONObject;
import com.ztqees.entity.FcEstate;
import com.ztqees.returnJson.ReturnObject;
import com.ztqees.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhouqien
 * @date 2021-10-17 15:41
 */
@RestController
public class EstateController {
    @Autowired
    private EstateService estateService;
    @RequestMapping("/estate/selectCompany")
    public JSONObject selectCompany(){
        System.out.println("房产这边成功进来了");
        List<String> tblCompanyNames = estateService.selectCompany();
        System.out.println(tblCompanyNames);
        return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject(tblCompanyNames)));
    }

    @RequestMapping("/estate/insertEstate")
    public JSONObject insertEstate(FcEstate fcEstate){
        System.out.println(fcEstate);
        Integer result =estateService.insertEstate(fcEstate);
        if (result == 0){
            return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject("房产编码已存在","0")));
        }else {
            return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject("插入房产成功","1")));
        }
    }
}
