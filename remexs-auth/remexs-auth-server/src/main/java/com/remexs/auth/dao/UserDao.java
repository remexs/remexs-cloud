package com.remexs.auth.dao;

import com.remexs.auth.entity.User;

import org.apache.ibatis.annotations.Mapper;

import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Mapper
public interface UserDao extends MybatisDao<User> {

}
