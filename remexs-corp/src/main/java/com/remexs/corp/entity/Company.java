package com.remexs.corp.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.PhysicalEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公司
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_corp")
public class Corp extends PhysicalEntity<Corp> {

	private static final long serialVersionUID = 1L;

	/**
	 * 公司名称
	 */
	private String name;
	/**
	 * 秘钥
	 */
	private String secret;

}
