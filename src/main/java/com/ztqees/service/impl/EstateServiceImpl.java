package com.ztqees.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ztqees.entity.FcBuilding;
import com.ztqees.entity.FcEstate;
import com.ztqees.entity.FcUnit;
import com.ztqees.mapper.FcBuildingMapper;
import com.ztqees.mapper.FcEstateMapper;
import com.ztqees.mapper.FcUnitMapper;
import com.ztqees.mapper.TblCompanyMapper;
import com.ztqees.service.EstateService;
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
            fcBuilding.setBuildingCode("Bzz"+(i+1));
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
            fcUnit.setUnitCode("ztqU-"+(i+1));
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
}
