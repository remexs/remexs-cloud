package com.remexs.common.response;

import com.remexs.common.exception.ErrCode;

public class ResultUtils {
	public static <T> Result<T> ok() {
		return new Result<T>().ok();
	}

	public static <T> Result<T> ok(T t) {
		return new Result<T>().ok(t);
	}

	public static <T> Result<T> ok(String msg, T data) {
		return new Result<T>().ok(msg, data);
	}

	/**
	 * 自定义返回错误
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> Result<T> error(int code, String msg) {
		return new Result<T>().error(code, msg);
	}
	/**
	 * 自定义返回错误
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> Result<T> error( String msg) {
		return new Result<T>().error(ErrCode.INVALID_REQUEST.getCode(), msg);
	}
}
