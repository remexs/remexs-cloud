package com.remexs.common.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author remexs
 *
 */
public interface Dto extends Map<String, Object>{
	/**
	 * 以Integer类型返回属性
	 * 
	 * @param key
	 * @return Integer 键值
	 */
	Integer getInteger(String key);
	
	/**
	 * 以BigInteger类型返回属性
	 * 
	 * @param key
	 * @return BigInteger 键值
	 */
	BigInteger getBigInteger(String key);

	/**
	 * 以Long类型返回属性
	 * 
	 * @param key
	 * @return Long 键值
	 */
	Long getLong(String key);

	/**
	 * 以String类型返回属性
	 * 
	 * @param key
	 * @return String 键值
	 */
	String getString(String key);

	/**
	 * 以BigDecimal类型返回属性
	 * 
	 * @param key
	 * @return BigDecimal 键值
	 */
	BigDecimal getBigDecimal(String key);

	/**
	 * 以Date类型返回属性
	 * 
	 * @param key
	 * @return Date 键值(yyyy-MM-dd)
	 */
	Date getDate(String key);

	/**
	 * 以Timestamp类型返回属性
	 * 
	 * @param key
	 * @return Timestamp 键值(yyyy-MM-dd HH:mm:ss)
	 */
	Timestamp getTimestamp(String key);

	/**
	 * 以Boolean类型返回属性
	 * 
	 * @param key
	 * @return Boolean 键值
	 */
	Boolean getBoolean(String key);

	/**
	 * 以List类型返回属性
	 * 
	 * @param key
	 * @return List 键值
	 */
	List<? extends Object> getList(String key);
	/**
	 * 修改返回实现类
	 */
	public Dto put(String key, Object value);
	
	
	public String toJson();
}
