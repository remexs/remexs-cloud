package com.remexs.common.converter;

import java.text.ParseException;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

import com.remexs.common.utils.DateUtils;
import com.remexs.common.utils.ObjectUtils;
/**
 * 字符串转换为时间类型
 * @author remexs
 *
 */
public class Str2DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String dateStr) {
		if (ObjectUtils.isEmpty(dateStr)) {
			return null;
		}
		try {
			return DateUtils.parseDate(dateStr, new String[] { "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","yyyy年MM月dd日","EEE MMM dd HH:mm:ss z yyyy" });
		} catch (ParseException e) {
			return null;
		}
	}
}