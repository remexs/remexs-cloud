package com.remexs.common.dto;

import java.util.Map;

import com.remexs.common.dto.impl.HashDto;

/**
 * <b>数据传输对象实例化辅助类</b>
 * 
 * @author remexs
 */
public class Dtos {

	/**
	 * 创建一个常规的Dto对象
	 * 
	 */
	public static Dto newDto() {
		return new HashDto();
	}

	/**
	 * 在Map的基础上克隆一个常规Dto对象
	 * 
	 */
	public static Dto newDto(Map<String, ?> map) {
		Dto newDto = new HashDto();
		newDto.putAll(map);
		return newDto;
	}
	/**
	 * 创建一个常规的Dto对象，并初始化一个键值对。
	 * 
	 * @param keyString
	 * @param valueObject
	 * @return
	 */
	public static Dto newDto(String keyString, Object valueObject) {
		Dto dto = new HashDto();
		dto.put(keyString, valueObject);
		return dto;
	}

}
