package com.remexs.data.mybatis.constant;

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
	 * 左模糊
	 */
	public static final String L_LIKE = "l_like_";

	/**
	 * 右模糊
	 */
	public static final String R_LIKE = "r_like_";

	/***
	 * 全模糊
	 */
	public static final String LIKE = "like_";
}
