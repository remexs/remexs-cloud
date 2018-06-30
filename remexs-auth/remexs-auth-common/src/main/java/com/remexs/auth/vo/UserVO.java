package com.remexs.auth.vo;

import com.remexs.common.vo.DataVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends DataVO<UserVO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3358783862715212796L;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 用户账号
	 */
	private String account;
}
