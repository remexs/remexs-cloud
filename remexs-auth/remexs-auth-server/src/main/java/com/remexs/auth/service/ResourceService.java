package com.remexs.auth.service;

import com.remexs.auth.entity.Resource;
import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 服务API接口分配 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-07-04
 */
public interface ResourceService extends MybatisService<Resource> {
	/**
	 * 根据API代码获得API详情
	 * @param code
	 * @return
	 */
	Resource getBy(String code);
	
	public boolean existBy(String code);
}
