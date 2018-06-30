package com.remexs.auth.service.impl;

import com.remexs.auth.entity.Resource;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.auth.dao.ResourceDao;
import com.remexs.auth.service.ResourceService;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
@Service
public class ResourceServiceImpl extends MybatisServiceImpl<ResourceDao, Resource> implements ResourceService {
	@Override
	public boolean insertOrUpdate(Resource resource) {
		resource.setId(null);
		Resource oldResource=this.selectOne(Condition.create().eq("code", resource.getCode()));
		if(ObjectUtils.isEmpty(oldResource)) {
			return this.insert(resource);
		}else {
			String id=oldResource.getId();
			oldResource.copyFrom(resource);
			oldResource.setId(id);
			return this.updateById(oldResource);
		}
	}
}
