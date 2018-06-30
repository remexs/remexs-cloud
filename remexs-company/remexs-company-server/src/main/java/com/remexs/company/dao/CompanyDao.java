package com.remexs.company.dao;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.company.entity.Company;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 公司 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@Mapper
public interface CompanyDao extends MybatisDao<Company> {

}
