package com.remexs.auth.service;

import com.remexs.auth.entity.Resource;
import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
public interface ResourceService extends MybatisService<Resource> {
	/**
	 * 新增或修改资源
	 */
	public boolean insertOrUpdate(Resource resource);
}
