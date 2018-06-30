package com.remexs.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.auth.entity.UserRole;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
@Mapper
public interface UserRoleDao extends MybatisDao<UserRole> {

}
