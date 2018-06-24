package com.remexs.web.utils;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.remexs.common.dto.Dto;
import com.remexs.common.dto.impl.HashDto;
/**
 * @description 请求参数获取
 * 
 * @author remexs
 * @email bigdrone@163.com
 * @date 2017年11月6日 下午5:14:52
 */
public class WebUtils {
	public static Dto getParamsAsDto(HttpServletRequest request) {
		Dto dto = new HashDto();
		Map<String, String[]> map = request.getParameterMap();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Iterator<String> keyIterator = (Iterator) map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = map.get(key)[0];
			dto.put(key, value);
		}
		return dto;
	}
}
