package com.remexs.auth.service.impl;

import com.remexs.auth.entity.Server;
import com.remexs.auth.entity.Resource;
import com.remexs.auth.entity.ServerResource;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.auth.dao.ServerResourceDao;
import com.remexs.auth.service.ResourceService;
import com.remexs.auth.service.ServerResourceService;
import com.remexs.auth.service.ServerService;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端接口分配 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Service
public class ServerResourceServiceImpl extends MybatisServiceImpl<ServerResourceDao, ServerResource> implements ServerResourceService {
	@Override
	public boolean insertOrUpdate(ServerResource serverResource) {
		String serverId = serverResource.getServerId();
		String resourceId = serverResource.getResourceId();
		if (!existBy(serverId, resourceId)) {
			return this.insert(serverResource);
		} else {
			serverResource.copyFrom(this.getBy(serverId, resourceId));
			return true;
		}
	}

	@Override
	public boolean existBy(String serverId, String resourceId) {
		return this.selectCount(Condition.create().eq("server_id", serverId).and().eq("resource_id", resourceId)) > 0;
	}

	@Override
	public ServerResource getBy(String serverId, String resourceId) {
		return this.selectOne(Condition.create().eq("server_id", serverId).and().eq("resource_id", resourceId));
	}
}
