package com.ztqees;

import com.ztqees.entity.TblUserRecord;
import com.ztqees.mapper.MyMapper;
import com.ztqees.mapper.TblLoginLogMapper;
import com.ztqees.mapper.TblUserRecordMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhouqien
 * @date 2021-10-10 15:19
 */
@SpringBootTest
public class ZTest {
    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;
    @Autowired
    private MyMapper myMapper;
    @Test
    public void test(){
        TblUserRecord admin = tblUserRecordMapper.login("admin", "1");
        System.out.println(admin);
    }
    @Test
    public void testtwo(){
        int all = myMapper.all();
        System.out.println(all);
    }
}
