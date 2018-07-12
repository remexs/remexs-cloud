package com.remexs.corp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.corp.entity.Department;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 公司部门 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@Mapper
public interface DepartmentDao extends MybatisDao<Department> {

}
