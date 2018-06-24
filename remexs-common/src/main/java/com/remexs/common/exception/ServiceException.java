package com.remexs.common.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 公共 业务 异常类
 */
public class ServiceException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 56263647416715478L;

	private Logger logger = LoggerFactory.getLogger(getClass());

    String errmsg;
    int errcode;
	public ServiceException(String errmsg){
		this.errcode=ErrCode.UN_KNOW_ERROR.getCode();
		this.errmsg=errmsg;
	}
	public ServiceException(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	public ServiceException(int errcode, String errmsg, Throwable cause) {
		super(errmsg, cause);
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	
}
