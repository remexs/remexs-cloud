package com.remexs.common.vo;

import java.io.Serializable;

/**
 * 实体类
 * 
 * @author remexs
 *
 */
public abstract class DataVO<T> extends BaseVO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5304391146865225655L;
	/**
	 * 实体编码
	 */
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
