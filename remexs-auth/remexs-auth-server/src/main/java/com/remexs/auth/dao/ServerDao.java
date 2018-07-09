package com.remexs.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.auth.entity.Server;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-07-04
 */
@Mapper
public interface ServerDao extends MybatisDao<Server> {

}
