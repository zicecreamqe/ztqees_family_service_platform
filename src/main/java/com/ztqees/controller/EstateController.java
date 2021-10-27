package com.ztqees.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ztqees.entity.FcBuilding;
import com.ztqees.entity.FcCell;
import com.ztqees.entity.FcEstate;
import com.ztqees.entity.FcUnit;
import com.ztqees.vo.CellMessage;
import com.ztqees.vo.UnitMessage;
import com.ztqees.returnJson.ReturnObject;
import com.ztqees.service.EstateService;
import javafx.scene.control.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.Arrays;
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

    @RequestMapping("/estate/selectUnit")
    public JSONObject selectUnit(@RequestBody UnitMessage[] unitMessages){
        System.out.println("ztqees selectUnit");
        System.out.println(unitMessages);
        List<FcUnit> fcUnitList =new ArrayList<>();
        for (UnitMessage unitMessage : unitMessages) {
            List<FcUnit> fcUnits = estateService.selectUnit(unitMessage);
            fcUnitList.addAll(fcUnits);
        }
        return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject(fcUnitList)));
    }

    @RequestMapping("/estate/updateUnit")
    public JSONObject updateUnit(FcUnit fcUnit){
        System.out.println(fcUnit);
        Integer rows = estateService.updateUnit(fcUnit);
        if (rows == 1){
            return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject("插入（更新）单元数据成功啦！！")));
        }else {
            return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject("插入（更新）单元数据失败奥！")));
        }
    }

    @RequestMapping("/estate/selectCell")
    public JSONObject selectCell(@RequestBody List<CellMessage> cellMessageList){
        System.out.println(cellMessageList);
        List<FcCell> cellList =new ArrayList<>();
        /**
         * 各个单元中的插入操作（跟楼宇没有关系）（其实这里我写的有点毛病，应该将下面这个各个单元的插入
         * 操作的这个for循环也放到service层中去实现才对，controller层中应该没有逻辑才行
         * 这里比较正确的代码写法可以去看看笔记上面的代码
         */
        for (CellMessage cellMessage : cellMessageList) {
            List<FcCell> cells = estateService.selectCell(cellMessage);
            cellList.addAll(cells);
        }
        return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject(cellList)));
    }

    @RequestMapping("/estate/selectBuildingNameByEstate")
    public JSONObject selectBuildingNameByEstate(String estateCode){
        System.out.println("ztqees selectBuildingNameByEstate success");
        System.out.println(estateCode);
        List<FcBuilding> fcBuildings = estateService.selectBuildingNameByEstate(estateCode);
        // 这里的后台他说有问题，还说我已经发现了，但是我还没有发现----------------------------------------
        System.out.println("fcBuildings" + fcBuildings);
        return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject(fcBuildings)));
    }

    @RequestMapping("/estate/selectUnitNameByBuilding")
    public JSONObject selectUnitNameByBuilding(String buildingCode){
        System.out.println("ztqees selectUnitNameByBuilding success");
        System.out.println(buildingCode);
        List<FcUnit> fcUnits = estateService.selectUnitNameByBuilding(buildingCode);
        System.out.println("fcUnit----" + fcUnits);
        return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject(fcUnits)));
    }

    @RequestMapping("/estate/selectCellNameByUnit")
    public JSONObject selectCellNameByUnit(String unitCode){
        System.out.println("ztqees selectCellNameByUnit success");
        System.out.println(unitCode);
        List<FcCell> fcCells = estateService.selectCellNameByUnit(unitCode);
        System.out.println("fcCell-----" + fcCells);
        return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject(fcCells)));
    }
}
