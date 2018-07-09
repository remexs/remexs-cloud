package com.remexs.auth.service.impl;

import com.remexs.auth.entity.RoleResource;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.auth.dao.RoleResourceDao;
import com.remexs.auth.service.RoleResourceService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色资源关联表 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Service
public class RoleResourceServiceImpl extends MybatisServiceImpl<RoleResourceDao, RoleResource> implements RoleResourceService {


	@Override
	public boolean insertOrUpdate(RoleResource roleResource) {
		String roleId=roleResource.getRoleId();
		String resourceId=roleResource.getResourceId();
		if(!this.existBy(roleId, resourceId)) {
			return this.insert(roleResource);
		}else {
			roleResource.copyFrom(this.getBy(roleId, resourceId));
			return true;
		}
	}

	@Override
	public boolean existBy(String roleId,String resourceId) {
		return this.selectCount(Condition.create().eq("role_id", roleId).and().eq("resource_id", resourceId))>0;
	}
	

	@Override
	public RoleResource getBy(String roleId, String resourceId) {
		return this.selectOne(Condition.create().eq("role_id", roleId).and().eq("resource_id", resourceId));
	}
}
