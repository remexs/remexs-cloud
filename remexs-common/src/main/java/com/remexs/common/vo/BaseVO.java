package com.remexs.common.vo;

import java.io.Serializable;

import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.utils.JsonUtils;
import com.remexs.common.utils.ObjectUtils;

/**
 * 实体类
 * 
 * @author remexs
 *
 */
public abstract class BaseVO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5304391146865225655L;
	/**
	 * 将当前对象转换为Dto对象
	 * 
	 * @return dto 返回的Dto对象
	 */
	public Dto toDto() {
		return ObjectUtils.copy(this, Dtos.newDto());
	}
	@SuppressWarnings("unchecked")
	public T copyFrom(Object from) {
		return (T) ObjectUtils.copy(from, this);
	}
	public String toJson() {
		return JsonUtils.toJson(this);
	}
}
