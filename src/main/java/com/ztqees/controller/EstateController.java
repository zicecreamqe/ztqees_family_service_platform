package com.ztqees.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ztqees.entity.FcBuilding;
import com.ztqees.entity.FcEstate;
import com.ztqees.returnJson.ReturnObject;
import com.ztqees.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 此处应该完成的是楼宇的查询功能
     * 在进行查询之前，因为数据库中没有原始数据，因此需要用户先插入数据然后再将数据回显更新
     * @param buildingNumber
     * @param estateCode
     * @return
     */
    @RequestMapping("/estate/selectBuilding")
    public JSONObject selectBuilding(Integer buildingNumber, String estateCode){
        List<FcBuilding> fcBuildings =estateService.selectBuilding(buildingNumber,estateCode);
        System.out.println(fcBuildings);
        return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject(fcBuildings)));
    }

    /**
     * 完成楼宇的插入（更新）功能
     * @param fcBuilding
     * @return
     */
    @RequestMapping("/estate/updateBuilding")
    public JSONObject updateBuilding(FcBuilding fcBuilding){
        System.out.println(fcBuilding);
        Integer update = estateService.updateBuilding(fcBuilding);
        System.out.println(update);
        if (update == 1){
            return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject("插入（更新）楼宇成功")));
        }else {
            return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject("插入（更新）楼宇失败")));
        }
    }
}
