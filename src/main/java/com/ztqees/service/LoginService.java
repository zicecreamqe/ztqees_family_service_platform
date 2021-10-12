package com.ztqees.service;

import com.ztqees.entity.TblUserRecord;

public interface LoginService {
    TblUserRecord login(String username,String password);
}
