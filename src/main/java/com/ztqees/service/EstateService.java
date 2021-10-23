package com.ztqees.service;

import com.ztqees.entity.FcBuilding;
import com.ztqees.entity.FcEstate;
import com.ztqees.entity.FcUnit;
import com.ztqees.vo.UnitMessage;

import java.util.List;

public interface EstateService {

    List<String> selectCompany();

    Integer insertEstate(FcEstate fcEstate);

    List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode);

    Integer updateBuilding(FcBuilding fcBuilding);

    List<FcUnit> selectUnit(UnitMessage unitMessage);

    Integer updateUnit(FcUnit fcUnit);
}
