package com.remexs.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.auth.entity.RoleResource;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 角色服务关联表 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Mapper
public interface RoleResourceDao extends MybatisDao<RoleResource> {

}
