package com.remexs.corp.dao;

import com.remexs.corp.entity.Corp;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 公司 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Mapper
public interface CorpDao extends MybatisDao<Corp> {

}
