package com.remexs.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.auth.entity.ServerResource;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 客户端接口分配 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Mapper
public interface ServerResourceDao extends MybatisDao<ServerResource> {
	
}
