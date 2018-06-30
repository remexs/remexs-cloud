package com.remexs.common.context;

import java.util.HashMap;
import java.util.Map;

import com.remexs.common.constants.GlobalConstants;
import com.remexs.common.utils.TypeUtils;

/**
 * 用户上下文工具类
 * 
 * @author remexs
 *
 */
public class UserContext {
	
    
	public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

	public static void set(String key, Object value) {
		Map<String, Object> map = threadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			threadLocal.set(map);
		}
		map.put(key, value);
	}

	public static Object get(String key) {
		Map<String, Object> map = threadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			threadLocal.set(map);
		}
		return map.get(key);
	}

	public static String getUserID() {
		Object value = get(GlobalConstants.CONTEXT_KEY_USER_ID);
		return returnObjectValue(value);
	}

	public static String getUserName() {
		Object value = get(GlobalConstants.CONTEXT_KEY_USER_NAME);
		return returnObjectValue(value);
	}
	public static String getUserAccount() {
		Object value = get(GlobalConstants.CONTEXT_KEY_USER_ACCOUNT);
		return TypeUtils.convert2String(value);
	}

	public static String getToken() {
		Object value = get(GlobalConstants.CONTEXT_KEY_USER_TOKEN);
		return TypeUtils.convert2String(value);
	}


	public static void setUserID(String userID) {
		set(GlobalConstants.CONTEXT_KEY_USER_ID, userID);
	}

	public static void setUsername(String username) {
		set(GlobalConstants.CONTEXT_KEY_USER_NAME, username);
	}

	public static void setUserAccount(String account) {
		set(GlobalConstants.CONTEXT_KEY_USER_ACCOUNT, account);
	}
	
	public static void setToken(String token) {
		set(GlobalConstants.CONTEXT_KEY_USER_TOKEN, token);
	}
	
	private static String returnObjectValue(Object value) {
		return value == null ? null : value.toString();
	}

	public static void remove() {
		threadLocal.remove();
	}
}
