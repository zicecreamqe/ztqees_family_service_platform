package com.ztqees.service;

import com.ztqees.entity.FcBuilding;
import com.ztqees.entity.FcEstate;

import java.util.List;

public interface EstateService {

    List<String> selectCompany();

    Integer insertEstate(FcEstate fcEstate);

    List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode);

    Integer updateBuilding(FcBuilding fcBuilding);
}
