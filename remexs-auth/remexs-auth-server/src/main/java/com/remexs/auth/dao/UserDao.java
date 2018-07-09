package com.remexs.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.remexs.auth.entity.User;
import com.remexs.data.mybatis.dao.MybatisDao;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Mapper
public interface UserDao extends MybatisDao<User> {
	/**
	 * 根据用户编码和资源编码查询用户资源授权总数
	 * @param userId
	 * @param resourceCode
	 * @return
	 */
	Integer queryPromiseCountByUserIdAndResourceCode(@Param("userId")String userId,@Param("resourceCode")String resourceCode);
}
