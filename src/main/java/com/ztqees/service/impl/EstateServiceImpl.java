package com.ztqees.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ztqees.entity.FcEstate;
import com.ztqees.entity.TblCompany;
import com.ztqees.mapper.FcEstateMapper;
import com.ztqees.mapper.TblCompanyMapper;
import com.ztqees.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Integer insertEstate(FcEstate fcEstate) {
        System.out.println("hahahahahaha"+fcEstate);
        int result = 0;
        QueryWrapper<FcEstate> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate fc = fcEstateMapper.selectOne(queryWrapper);
        if (fc == null){
            result = fcEstateMapper.insert(fcEstate);
        }
        return result;
    }
}
