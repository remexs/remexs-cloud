package com.remexs.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * json 工具类
 * @author remexs
 *
 */
public class JsonUtils {
	/***
	 * 解析为字符串
	 *
	 * @param jsonString
	 *            单层嵌套
	 * @param key
	 * @return
	 */
	public static String getString(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getString(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 解析为String 两层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static String getString(String jsonString, String key, String skey) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				JSONObject jsonObject1 = jsonObject.getJSONObject(key);
				String jsonObject2 = jsonObject1.getString(skey);
				return jsonObject2;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 解析为布尔 单层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getBoolean(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 解析为数字 单层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static Integer getInteger(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getInteger(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 解析为Int 两层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static int getInteger(String jsonString, String key, String skey) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				JSONObject jsonObject1 = jsonObject.getJSONObject(key);
				int jsonObject2 = jsonObject1.getInteger(skey);
				return jsonObject2;
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/***
	 * 解析为长位十进制数 单层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static BigDecimal getBigDecimal(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getBigDecimal(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 解析为双精度 单层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static Double getDouble(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getDouble(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 解析为浮点数 单层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static Float getFloat(String jsonString, String key) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(jsonString);
				return jsonObject.getFloat(key);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 解析为对象 单层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> T toJavaObject(String jsonString, String key, Class<T> t) {
		if (jsonString != null) {
			try {
				JSONObject jsonObj = JSONObject.parseObject(jsonString);
				return JSONObject.toJavaObject(jsonObj.getJSONObject(key), t);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/***
	 * 解析为列表 单层嵌套
	 *
	 * @param jsonString
	 * @param key
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> ArrayList<T> toList(String jsonString, String key, Class<T> t) {
		ArrayList<T> list = new ArrayList<T>();
		if (jsonString != null && jsonString.length() > 0) {
			try {
				JSONObject jsonObj = JSONObject.parseObject(jsonString);
				JSONArray inforArray = jsonObj.getJSONArray(key);
				for (int index = 0; index < inforArray.size(); index++) {
					list.add(JSONObject.toJavaObject(inforArray.getJSONObject(index), t));
				}
			} catch (Exception e) {
			}
		}
		return list;
	}

	/***
	 * 直接解析为bean
	 *
	 * @param jsonString
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> T toJavaObject(String jsonString, Class<T> t) {
		try {
			if (jsonString != null && jsonString.length() > 0) {
				return JSON.parseObject(jsonString, t);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 直接解析为list
	 *
	 * @param jsonString
	 * @param t 
	 * @param <T>
	 * @return
	 */
	public static <T> ArrayList<T> toList(String jsonString, Class<T> t) {
		ArrayList<T> list = null;
		try {
			list = new ArrayList<>();
			if (jsonString != null && jsonString.length() > 0) {
				list.addAll(JSON.parseArray(jsonString, t));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/***
	 * 将列表转换为json
	 *
	 * @param listBean
	 * @return
	 */
	public static <T> String toJson(ArrayList<T> listBean) {
		String jsonString = JSON.toJSONString(listBean);
		return jsonString;
	}

	/***
	 * 将类转为json
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> String toJson(Object obj) {
		String jsonsString = JSON.toJSONString(obj);
		return jsonsString;
	}
}
