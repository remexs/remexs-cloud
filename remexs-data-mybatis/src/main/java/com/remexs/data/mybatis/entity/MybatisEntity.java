package com.remexs.data.mybatis.entity;

import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.utils.JsonUtils;
import com.remexs.common.utils.ObjectUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实体模型超类（包含物理模型和关系模型）
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = true)

public abstract class MybatisEntity<T extends Model<T>> extends Model<T> {

	private static final long serialVersionUID = 3682584189038868800L;
	
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
}
