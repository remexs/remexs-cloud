package com.remexs.data.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.utils.JsonUtils;
import com.remexs.common.utils.ObjectUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物理模型父类
 * 
 * @author remexs
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MybatisPhysicalEntity <T extends MybatisEntity<T>> extends MybatisEntity<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6892055532426961097L;

	/**
	 * 创建者
	 */
	@TableField(value = "create_id", fill = FieldFill.INSERT)
	public String createId;
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	public Date createTime;
	/**
	 * 修改者
	 */
	@TableField(value = "update_id", fill = FieldFill.UPDATE)
	
	public String updateId;
	/**
	 * 修改时间
	 */
	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")  
	public Date updateTime;
	/**
	 * 实体编码
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	public String id;


	/**
	 * 将当前对象转换为Dto对象
	 *
	 * @return dto 返回的Dto对象
	 */
	public Dto toDto() {
		return ObjectUtils.copy(this, Dtos.newDto());
	}
	@SuppressWarnings("unchecked")
	public T copyFrom(Object from) {
		return (T) ObjectUtils.copy(from, this);
	}

	public String toJson() {
		return JsonUtils.toJson(this);
	}

	@Override
	public Serializable pkVal() {
		return this.id;
	}

}
