package com.ztqees.mapper;

import com.ztqees.entity.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author ztqees
 * @since 2021-10-10
 */
public interface TblCompanyMapper extends BaseMapper<TblCompany> {

    List<String> selectCompany();
}
