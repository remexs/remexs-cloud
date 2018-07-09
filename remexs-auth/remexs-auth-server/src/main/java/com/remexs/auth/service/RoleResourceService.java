package com.remexs.auth.service;

import com.remexs.auth.entity.RoleResource;
import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 角色服务关联表 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
public interface RoleResourceService extends MybatisService<RoleResource> {

	public boolean existBy(String roleId,String resourceId);
	
	public RoleResource getBy(String roleId,String resourceId);
}
