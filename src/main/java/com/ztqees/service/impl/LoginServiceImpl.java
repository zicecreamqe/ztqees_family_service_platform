package com.ztqees.service.impl;

import com.ztqees.entity.TblUserRecord;
import com.ztqees.mapper.TblUserRecordMapper;
import com.ztqees.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouqien
 * @date 2021-10-11 15:22
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;
    @Override
    public TblUserRecord login(String username,String password) {
        return tblUserRecordMapper.login(username, password);
    }
}
