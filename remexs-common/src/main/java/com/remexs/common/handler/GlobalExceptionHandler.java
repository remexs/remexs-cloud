package com.remexs.common.handler;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.remexs.common.dto.Dto;
import com.remexs.common.exception.ErrCode;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;

/**
 * 全局异常处理
 * @author remexs
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	/**
	 * 参数错误
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public Result<Dto> bindExceptionHandler(BindException e) {
		return ResultUtils.error(ErrCode.INVALID_REQUEST.getCode(), e.getMessage());
	}
	/**
	 * 业务异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public Result<Dto> handleBlifeException(ServiceException e) {
		return ResultUtils.error(e.getErrcode(), e.getErrmsg());
	}

	/**
	 * 400 - 参数错误 
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result<Dto> handleHttpMessageNotReadableException(Exception e) {
		return general(ErrCode.INVALID_REQUEST.getCode(), ErrCode.INVALID_REQUEST.getMsg());

	}
//	/**
//	 * 401 - 没有权限
//	 */
//	@ExceptionHandler(auth.class)
//	public Result<Dto> handl(Exception e) {
//		return general(ErrCode.UNAUTHORIZED.getCode(), e.getMessage());
//
//	}
	/**
	 * 403 - Method Not Allowed
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result<Dto> handleHttpRequestMethodNotSupportedException(Exception e) {
		return general(ErrCode.METHOD_NOT_ALLOWED.getCode(), ErrCode.METHOD_NOT_ALLOWED.getMsg());
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Result<Dto> handleHttpMediaTypeNotSupportedException(Exception e) {

		return general(ErrCode.UNSUPPORTED_MEDIA_TYPE.getCode(), ErrCode.UNSUPPORTED_MEDIA_TYPE.getMsg());

	}

	/**
	 * 500 - Internal Server Error
	 */
	@ExceptionHandler(Exception.class)
	public Result<Dto> handleException(Exception e) {
		e.printStackTrace();
		return general(ErrCode.INTERNAL_SERVER_ERROR.getCode(), ErrCode.INTERNAL_SERVER_ERROR.getMsg());
	}

	/**
	 * sql 异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	public Result<Dto> handleSql(Exception e) {
		return general(ErrCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
	}

	/**
	 * 返回 spring 统一异常 后的信息
	 * 
	 * @param httpStatus
	 * @return
	 */
	private Result<Dto> general(int errcode,String errmsg) {
		return ResultUtils.error(errcode, errmsg);
	}
}
