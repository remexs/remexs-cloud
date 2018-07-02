package com.remexs.common.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.remexs.common.utils.ObjectUtils;
/**
 * 日期转时间转换器
 * @author remexs
 *
 */
public class Date2StrCoverter implements Converter {

	private static Logger logger = LoggerFactory.getLogger(Date2StrCoverter.class);

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> myClass, Object myObj) {
		if (ObjectUtils.isEmpty(myObj)) {
			return null;
		}
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return (T) df.parse(myObj.toString());
		} catch (ParseException e) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				return (T) df.parse(myObj.toString());
			} catch (ParseException e1) {
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
					return (T) df.parse(myObj.toString());
				} catch (ParseException e2) {
					try {
						SimpleDateFormat dfParse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
						return (T) dfParse.parse(myObj.toString());
					} catch (ParseException e3) {
						logger.warn("对象间日期类型属性复制时由于格式问题解析失败，属性值【{}】复制失败。", myObj.toString());
						e3.printStackTrace();
					}
				}
			}
		}
		return null;
	}

}