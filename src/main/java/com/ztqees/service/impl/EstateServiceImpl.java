package com.ztqees.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ztqees.entity.FcBuilding;
import com.ztqees.entity.FcCell;
import com.ztqees.entity.FcEstate;
import com.ztqees.entity.FcUnit;
import com.ztqees.mapper.*;
import com.ztqees.service.EstateService;
import com.ztqees.vo.CellMessage;
import com.ztqees.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouqien
 * @date 2021-10-17 15:56
 */
@Service
public class EstateServiceImpl implements EstateService {
    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

    @Override
    public List<String> selectCompany() {
        return tblCompanyMapper.selectCompany();
    }


    /**
     * 在此逻辑中需要先做判断，判断数据库中是否已经存在编码，如果存在，那么对用户发出提示（不能添加了）
     * @param fcEstate
     * @return
     */
    @Override
    public Integer insertEstate(FcEstate fcEstate) {
        int result = 0;
        QueryWrapper<FcEstate> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate fc = fcEstateMapper.selectOne(queryWrapper);
        if (fc == null){
            result = fcEstateMapper.insert(fcEstate);
        }
        return result;
    }

    @Autowired
    private FcBuildingMapper fcBuildingMapper;
    @Override
    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode) {
        List<FcBuilding> fcBuildings =new ArrayList<>();
        for (Integer i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding =new FcBuilding();
            fcBuilding.setBuildingCode(estateCode+"Bzz"+(i+1));
            fcBuilding.setBuildingName("第"+(i+1)+"号楼ztqees");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }

    @Override
    public Integer updateBuilding(FcBuilding fcBuilding) {
        return fcBuildingMapper.updateById(fcBuilding);
    }

    @Autowired
    private FcUnitMapper fcUnitMapper;
    @Override
    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        List<FcUnit> fcUnits =new ArrayList<>();
        for (int i = 0; i < unitMessage.getunitCount(); i++) {
            FcUnit fcUnit =new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode(unitMessage.getBuildingCode()+"ztqU-"+(i+1));
            fcUnit.setUnitName("ztq第"+(i+1)+"单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }

    @Override
    public Integer updateUnit(FcUnit fcUnit) {
        return fcUnitMapper.updateById(fcUnit);
    }

    @Autowired
    private FcCellMapper fcCellMapper;
    @Override
    public List<FcCell> selectCell(CellMessage cellMessage) {
        List<FcCell> fcCellList = new ArrayList<>();
        Integer startFloor = cellMessage.getStartFloor();
        Integer stopFloor = cellMessage.getStopFloor();
        Integer startCellId = cellMessage.getStartCellId();
        Integer stopCellId = cellMessage.getStopCellId();
        Integer FloorNumber = stopFloor - startFloor + 1;
        Integer CellNumber = stopCellId - startCellId + 1;
        // 各个楼层中的插入操作
        for (int i = 1; i <= FloorNumber; i++) {
            // 各个房间的插入操作
            for (int j = 1; j <= CellNumber; j++) {
                FcCell fcCell = new FcCell();
                fcCell.setUnitCode(cellMessage.getUnitCode());
                fcCell.setCellName(i+"00"+j);
                fcCell.setFloorNumber(i);
                fcCell.setCellCode(cellMessage.getUnitCode()+"C"+i+"000"+j);
                fcCellMapper.insert(fcCell);
                fcCellList.add(fcCell);
            }
        }
        return fcCellList;
    }

    @Override
    public List<FcBuilding> selectBuildingNameByEstate(String estateCode) {
        QueryWrapper<FcBuilding> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("estate_code", estateCode);
        queryWrapper.select("building_code","building_name");
        return fcBuildingMapper.selectList(queryWrapper);
    }

    @Override
    public List<FcUnit> selectUnitNameByBuilding(String buildingCode) {
        QueryWrapper<FcUnit> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("building_code", buildingCode);
        queryWrapper.select("unit_code","unit_name");
        return fcUnitMapper.selectList(queryWrapper);
    }

    @Override
    public List<FcCell> selectCellNameByUnit(String unitCode) {
        QueryWrapper<FcCell> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("unit_code", unitCode);
        return fcCellMapper.selectList(queryWrapper);
    }

    @Override
    public Integer updateCell(FcCell fcCell) {
        return fcCellMapper.updateById(fcCell);
    }

    @Override
    public List<FcEstate> selectEstate(Integer companyId) {
        QueryWrapper<FcEstate> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("company", companyId);
        return fcEstateMapper.selectList(queryWrapper);
    }

    //这里在typora笔记中做的更好（他重复利用了之前的selectBuilding的controller优化了代码）
    @Override
    public List<FcBuilding> BatchSelectBuilding(String estateCode, Integer buildingNumber) {
        QueryWrapper<FcBuilding> queryWrapper =new QueryWrapper<>();
        int lastNum = 0;
        queryWrapper.eq("estate_code", estateCode);
        List<FcBuilding> fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        //再搞一个已经存在的数据集合，为了之后能查询出当前添加的数据集
        List<FcBuilding> fcBuilding2 = fcBuildingMapper.selectList(queryWrapper);
        if(fcBuildings.size() != 0){ // 当批插入的时候里面没有数据的时候
            String lastBuildingName = fcBuildings.get(fcBuildings.size() - 1).getBuildingName();
            String lastStringNum = lastBuildingName.replace("第", "").replace("号楼ztqees", "");
            System.out.println(lastStringNum+"------");
            lastNum = Integer.parseInt(lastStringNum);
            System.out.println(lastNum);
        }
        for (int i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding =new FcBuilding();
            fcBuilding.setBuildingCode(estateCode+"Bzz"+(lastNum+i+1));
            fcBuilding.setBuildingName("第"+(lastNum+i+1)+"号楼ztqees");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        QueryWrapper<FcBuilding> queryWrapper2 =new QueryWrapper<>();
        Integer lastId = fcBuilding2.get(fcBuilding2.size() - 1).getId();
        queryWrapper2.gt("id",lastId);
        List<FcBuilding> fcBuildingList = fcBuildingMapper.selectList(queryWrapper2);
        // 这里要是返回fcBuildings那就是返回数据库中所有的楼宇,现在是返回fcBuildingList,为返回添加的楼宇
        return fcBuildingList;
    }
    // 楼宇数据的更新操作也是重复利用了之前的controller

    // 同上面BatchSelectBuilding一样的操作
    @Override
    public List<FcUnit> BatchSelectUnit(UnitMessage unitMessage) {
        QueryWrapper<FcUnit> queryWrapper = new QueryWrapper<>();
        int lastNum = 0;
        queryWrapper.eq("building_code", unitMessage.getBuildingCode());
        List<FcUnit> fcUnits = fcUnitMapper.selectList(queryWrapper);
        if (fcUnits.size() != 0) { // 当批插入的时候里面没有数据的时候
            String lastUnitName = fcUnits.get(fcUnits.size() - 1).getUnitName();
            String lastStringNum = lastUnitName.replace("ztq第", "").replace("单元", "");
            System.out.println(lastStringNum+"=========");
            lastNum = Integer.parseInt(lastStringNum);
            System.out.println(lastNum);
        }
        for (int i = 0; i < unitMessage.getunitCount(); i++) {
            FcUnit fcUnit =new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode(unitMessage.getBuildingCode()+"ztqU-"+(lastNum+i+1));
            fcUnit.setUnitName("ztq第"+(lastNum+i+1)+"单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }
}
