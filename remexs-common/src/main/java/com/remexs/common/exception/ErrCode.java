package com.remexs.common.exception;

/**
 * Created by remexs on 2017/12/23.
 * <p>
 * Email bigdrone@163.com
 * <p>
 * Describe: http 状态码
 * ----------------------------------------------------------------------------
 * 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
 * 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
 * 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
 * 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
 * 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
 * 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
 * 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
 * 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
 * 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
 * 600 UN_KNOW_ERROR - 未知错误
 * ----------------------------------------------------------------------------
 */
public enum ErrCode {
	/** 操作成功 */
    OK(200,"操作成功"),
    /** 参数错误 */
    INVALID_REQUEST(400,"参数错误"),
    /**没有权限 */
    UNAUTHORIZED(401,"没有权限"),
    /** 禁止访问 */
    FORBIDDEN(403,"禁止访问"),
    /** 资源不存在 */
    NOT_FOUND(404,"资源不存在"),
    /** 请求的格式不正确 */
    NOT_ACCEPTABLE(406,"请求的格式不正确"),
    /** 操作成功 */
    GONE(410,"数据被删除"),
    /** 参数验证错误 */
    UNPROCESABLE_ENTITY(422,"参数验证错误"),
    /** 服务器发生错误 */
    INTERNAL_SERVER_ERROR(500,"服务器发生错误"),
    /** 未知错误 */
    UN_KNOW_ERROR(500,"未知错误"),
    /** 操作失败 */
    FAIL(501,"操作失败"),
    /** 类型转换错误 */
    CONVER_ERROR(500,"类型转换错误"),
    /** 用户名或密码错误 */
    USERNAME_OR_PASSWORD_ERR(2000,"用户名或密码错误"),
    /** 验证码不正确 */
    KAPTCHA_ERR(2001,"验证码不正确"),
    /** 账号被锁定 */
    ACCOUNT_LOCKED_ERR(2002,"账号被锁定"),
    /** 默认头像不可删除 */
    DELETE_DEFAULT_PHOTO_ERR(2003,"默认头像不可删除");

    private int code;
	private String msg;

	ErrCode(int code, String msg) {
		this.msg = msg;
		this.code = code;
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
}
