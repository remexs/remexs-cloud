package com.remexs.common.dto.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.remexs.common.dto.Dto;
import com.remexs.common.utils.JsonUtils;
import com.remexs.common.utils.TypeUtils;

/**
 * <b>数据传输对象实现</b>
 * <p>
 * 对原生Java Map类型的二次包装，提供<b><i>更加方便的存取API、更强的容错和类型转换机制。</i></b>
 * 在平台二次开发过程中具有很强的实用价值。 开发人员需熟练掌握其提供的相关API。
 * </p>
 * 
 * @author remexs
 */
public class HashDto extends HashMap<String, Object> implements Dto {

	private static Logger logger = LoggerFactory.getLogger(HashDto.class);

	private static final long serialVersionUID = 1L;

	/**
	 * 缺省构造函数
	 */
	public HashDto() {

	}

	/**
	 * 以Integer类型返回属性
	 * 
	 * @param key
	 * @return Integer 键值
	 * @throws Exception
	 */
	@Override
	public Integer getInteger(String key) {
		Object obj = TypeUtils.convert(get(key), "Integer", null);
		if (obj != null)
			return (Integer) obj;
		else
			return null;
	}

	/**
	 * 以BigInteger类型返回属性
	 * 
	 * @param key
	 * @return BigInteger 键值
	 */
	public BigInteger getBigInteger(String key) {
		BigInteger outValue = null;
		Object obj = get(key);
		if (obj instanceof BigInteger) {
			outValue = (BigInteger) obj;
		} else {
			outValue = new BigInteger(getString(key));
		}
		return outValue;
	}

	/**
	 * 以Long类型返回属性
	 * 
	 * @param key
	 * @return Long 键值
	 */
	@Override
	public Long getLong(String key) {
		Object obj = TypeUtils.convert(get(key), "Long", null);
		if (obj != null)
			return (Long) obj;
		else
			return null;
	}

	/**
	 * 以String类型返回属性
	 * 
	 * @param key
	 * @return String 键值
	 */
	@Override
	public String getString(String key) {
		Object obj = TypeUtils.convert(get(key), "String", null);
		if (obj != null)
			return (String) obj;
		else
			return "";
	}

	/**
	 * 以BigDecimal类型返回属性
	 * 
	 * @param key
	 * @return BigDecimal 键值
	 */
	@Override
	public BigDecimal getBigDecimal(String key) {
		Object obj = TypeUtils.convert(get(key), "BigDecimal", null);
		if (obj != null)
			return (BigDecimal) obj;
		else
			return null;
	}

	/**
	 * 以Date类型返回属性
	 * 
	 * @param key
	 * @return Date 键值(yyyy-MM-dd)
	 */
	@Override
	public Date getDate(String key) {
		Object obj = TypeUtils.convert(get(key), "Date", "yyyy-MM-dd");
		if (obj != null)
			return (Date) obj;
		else
			return null;
	}

	/**
	 * 以Timestamp类型返回属性
	 * 
	 * @param key
	 * @return Timestamp 键值(yyyy-MM-dd HH:mm:ss)
	 */
	@Override
	public Timestamp getTimestamp(String key) {
		Object obj = TypeUtils.convert(get(key), "Timestamp", "yyyy-MM-dd HH:mm:ss");
		if (obj != null)
			return (Timestamp) obj;
		else
			return null;
	}

	/**
	 * 以Boolean类型返回属性
	 * 
	 * @param key
	 * @return Boolean 键值
	 */
	@Override
	public Boolean getBoolean(String key) {
		Object obj = TypeUtils.convert(get(key), "Boolean", null);
		if (obj != null)
			return (Boolean) obj;
		else
			return null;
	}

	/**
	 * 以List类型返回属性
	 * 
	 * @param key
	 * @return List 键值
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Object> getList(String key) {
		return (List<? extends Object>) get(key);
	}

	public Dto put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	@Override
	public String toJson() {
		return JsonUtils.toJson(this);
	}
}
