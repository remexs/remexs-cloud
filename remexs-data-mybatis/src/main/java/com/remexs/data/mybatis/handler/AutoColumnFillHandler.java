package com.remexs.data.mybatis.handler;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.remexs.common.utils.ObjectUtils;

/**
 * mybatisplus自定义填充公共字段 ,即没有传的字段自动填充
 * 
 * @author remexs
 */
@Component
public class AutoColumnFillHandler extends MetaObjectHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 新增字段自动填充
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		logger.debug("insert set createTime createId param");
		Object createId = getFieldValByName("createId", metaObject);
		if(ObjectUtils.isEmpty(createId)) {
			setFieldValByName("createId", "0", metaObject);
		}
		Object createTime = getFieldValByName("createTime", metaObject);
		if(ObjectUtils.isEmpty(createTime)) {
			setFieldValByName("createTime", new Date(), metaObject);
		}
	}

	/**
	 * 修改字段自动填充
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		// TODO Auto-generated method stub
		logger.debug("update set  updateTime updateId param");
		Object updateId = getFieldValByName("updateId", metaObject);
		if(ObjectUtils.isEmpty(updateId)) {
			setFieldValByName("updateId", "0", metaObject);
		}
		setFieldValByName("updateTime", new Date(), metaObject);
	}

}
