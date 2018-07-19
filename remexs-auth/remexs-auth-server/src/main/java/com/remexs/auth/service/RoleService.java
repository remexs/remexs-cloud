package com.remexs.auth.service;

import java.util.List;

import com.remexs.auth.entity.Role;
import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
public interface RoleService extends MybatisService<Role> {
	/**
	 * 根据用户编码查询用户角色列表
	 * @param userId
	 * @return
	 */
	List<Role> queryListByUserId(String userId);
}
