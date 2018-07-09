package com.remexs.auth.service;

import com.remexs.auth.entity.ServerResource;
import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 客户端接口分配 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
public interface ServerResourceService extends MybatisService<ServerResource> {
	public boolean existBy(String serverId,String resourceId);
	public ServerResource getBy(String serverId,String resourceId);
}
