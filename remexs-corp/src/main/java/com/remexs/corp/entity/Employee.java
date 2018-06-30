package com.remexs.corp.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.PhysicalEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公司用户
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_corp_staff")
public class CorpStaff extends PhysicalEntity<CorpStaff> {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 账号
	 */
	private String account;

}
