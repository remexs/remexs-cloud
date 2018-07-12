package com.remexs.common.response;

import com.remexs.common.exception.ErrCode;
import com.remexs.common.utils.JsonUtils;

/**
 * 统一消息返回抽象类
 * 
 * @author remexs
 *
 * @param <T>
 */
public class Result<T> {
	private boolean success = true;
	private int code = 0;
	private String msg = "";
	private T data;

	public Result<T> ok() {
		this.success = true;
		this.code = ErrCode.OK.getCode();
		this.msg = ErrCode.OK.getMsg();
		this.data = null;
		return this;
	}

	public Result<T> ok(String msg, T data) {
		this.success = true;
		this.code = ErrCode.OK.getCode();
		this.msg = msg;
		this.data = null;
		return this;
	}

	public Result<T> ok(T data) {
		this.success = true;
		this.code = ErrCode.OK.getCode();
		this.msg = ErrCode.OK.getMsg();
		this.data = data;
		return this;
	}

	/**
	 * 设置错误信息
	 * 
	 * @param code
	 * @param msg
	 */
	public Result<T> error(int code, String msg) {
		this.success = false;
		this.code = code;
		this.msg = msg;
		this.data = null;
		return this;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String toJson() {
		return JsonUtils.toJson(this);
	}
}
