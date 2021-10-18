package com.ztqees;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ztqees.entity.FcEstate;
import com.ztqees.entity.TblUserRecord;
import com.ztqees.mapper.FcEstateMapper;
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
        TblUserRecord admin = tblUserRecordMapper.login("admin", "c4ca4238a0b923820dcc509a6f75849b");
        System.out.println(admin);
    }
    @Test
    public void testtwo(){
        int all = myMapper.all();
        System.out.println(all);
    }



    @Autowired
    private FcEstateMapper fcEstateMapper;
    /*@Test
    public void testInsertEstate(){
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code", 12);
        FcEstate fcEstate = fcEstateMapper.selectOne(queryWrapper);
        System.out.println(fcEstate);
        int insert = fcEstateMapper.insert(new FcEstate(1, "11", "11", "", null, null, null, null, 2.0, "", "", "", "", "", null, null, null, null, null, null, "", "0"));
        System.out.println(insert);
    }*/
}
