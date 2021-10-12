package com.ztqees.mapper;

import com.ztqees.entity.TblUserRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户档案 Mapper 接口
 * </p>
 *
 * @author ztqees
 * @since 2021-10-10
 */
public interface TblUserRecordMapper extends BaseMapper<TblUserRecord> {
    TblUserRecord login(@Param("username") String username, @Param("password")String password);
}
