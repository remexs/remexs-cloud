package com.remexs.auth.vo;

import java.util.List;

import com.remexs.auth.entity.Role;
import com.remexs.common.vo.DataVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVO extends DataVO<UserVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5740015999923489161L;

	private String name;
	private String account;
	private List<Role> roles;

}
