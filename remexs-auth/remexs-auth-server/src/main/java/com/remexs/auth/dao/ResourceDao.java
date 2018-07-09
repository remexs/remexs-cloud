package com.remexs.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.remexs.auth.entity.Resource;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 服务API接口分配 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-07-04
 */
@Mapper
public interface ResourceDao extends MybatisDao<Resource> {

}
