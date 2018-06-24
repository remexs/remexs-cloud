package com.remexs.data.mongodb.constant;

public interface SearchCons {
	/**
	 * 等于
	 */
	public static final String EQ = "eq_";
	/**
	 * 不等于
	 */
	public static final String NE = "ne_";
	/***
	 * 包含 - 多个值使用逗号分隔
	 */
	public static final String IN = "in_";
	/***
	 * 不包含-多个值使用逗号分隔
	 */
	public static final String NOT_IN = "not_in_";
	/***
	 * 大于
	 */
	public static final String GREAT = "great_";
	/***
	 * 小于
	 */
	public static final String LESS = "less_";

	/**
	 * 模糊查询
	 */
	public static final String REGX = "regx_";
}
