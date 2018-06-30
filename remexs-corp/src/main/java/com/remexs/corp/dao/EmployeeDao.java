package com.remexs.corp.dao;

import com.remexs.corp.entity.CorpStaff;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 公司用户 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Mapper
public interface CorpStaffDao extends MybatisDao<CorpStaff> {

}
