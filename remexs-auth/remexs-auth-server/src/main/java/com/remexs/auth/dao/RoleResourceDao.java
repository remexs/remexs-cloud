package com.remexs.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.auth.entity.RoleResource;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 角色资源 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
@Mapper
public interface RoleResourceDao extends MybatisDao<RoleResource> {

}
