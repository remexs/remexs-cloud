package com.remexs.common.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.remexs.common.exception.ErrCode;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.converter.Date2StrCoverter;
import com.remexs.common.dto.Dto;


/**
 * object 工具类
 * 
 * @author remexs
 *
 */
public class ObjectUtils extends org.springframework.util.ObjectUtils{
	/**
	 * Java对象之间属性值拷贝(Dto、Map、JavaBean)
	 * 
	 * @param pFromObj  Bean源对象
	 * @param pToObj Bean目标对象
	 */
	public static Object copy(Object from, Object to) {
		if (to != null) {
			try {
				// 支持属性空值复制
				BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
				// 日期类型复制
				Date2StrCoverter converter = new Date2StrCoverter();
				ConvertUtils.register(converter, java.util.Date.class);
				ConvertUtils.register(converter, java.sql.Date.class);
				BeanUtils.copyProperties(to,from);
			} catch (Exception e) {
				throw new ServiceException(ErrCode.CONVER_ERROR.getCode(), "将JavaBean属性值拷贝到Dto对象发生错误", e);
			}
			return to;
		}
		return null;
	}
	/**
	 * 将JavaBean对象中的属性值拷贝到Dto对象
	 * 
	 * @param from JavaBean对象源
	 * @param to Dto目标对象
	 * @return 
	 */
	public static Dto copy(Object from, Dto to) {
		if (to != null) {
			try {
				to.putAll(BeanUtils.describe(from));
				to.remove("class");
			} catch (Exception e) {
				throw new ServiceException(ErrCode.CONVER_ERROR.getCode(), "将JavaBean属性值拷贝到Dto对象发生错误", e);
			}
			return to;
		}
		return null;
	}
}
