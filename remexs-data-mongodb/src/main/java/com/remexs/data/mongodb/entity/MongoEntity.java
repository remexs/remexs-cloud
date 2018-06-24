package com.remexs.data.mongodb.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.utils.JsonUtils;
import com.remexs.common.utils.ObjectUtils;

import lombok.Data;
@Data
public class MongoEntity<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1657645236278573302L;
	@Id
	String id;
	
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
