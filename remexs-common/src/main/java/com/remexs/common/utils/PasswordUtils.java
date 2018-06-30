package com.remexs.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	/**
	 * 加密密码
	 * @param password 
	 * @return
	 */
	public static String encoder(String password) {
		return encoder.encode(password);
	}
	/**
	 * 验证密码是否正确
	 * @param password 密码
	 * @param matchPassword 待验证的密码
	 * @return
	 */
	public static boolean matches(String password,String matchPassword) {
		return encoder.matches(password, matchPassword);
	}
	
}
