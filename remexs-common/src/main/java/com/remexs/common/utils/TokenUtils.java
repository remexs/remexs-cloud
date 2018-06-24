package com.remexs.common.utils;

import java.util.Date;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class TokenUtils {
	private static final String privateKey = "fdas34ljfr好sja@#8$%dfkl;js&4*daklfjsdl;akfjsa342";
	
	public static String getToken(String userid, String date) {
        return Hashing
        		.md5()
        		.newHasher()
                .putString(userid, Charsets.UTF_8)
                .putString(privateKey, Charsets.UTF_8)
                .putString(date, Charsets.UTF_8)
                .hash()
                .toString();
    }
	
	public static String getToken(String userId, Date date) {
        return Hashing
        		.md5()
        		.newHasher()
        		.putString(userId, Charsets.UTF_8)
                .putString(privateKey, Charsets.UTF_8)
                .putString(DateUtils.getDateStr(date,"yyyyMMddHH"), Charsets.UTF_8)
                .hash()
                .toString();
    }
	public static String getToken(String userId) {
	    return Hashing
	    		.md5()
	    		.newHasher()
	    		.putString(userId, Charsets.UTF_8)
	            .putString(privateKey, Charsets.UTF_8)
	            .putString(DateUtils.getDateStr("yyyyMMddHH"), Charsets.UTF_8)
		        .hash()
		        .toString();
	}

	/**
	 * 2个小时内都校验通过
	 *
	 * @param token
	 * @param userId
	 * @return
	 */
	public static boolean validToken(String token, String userId) {
		String confirm = getToken(userId);
		String confirmNextHour = getToken(userId, DateUtils.getNextHourStr());
		if (confirm.equals(token) || confirmNextHour.equals(token)) {
			return true;
		} else {
			return false;
		}
	}
}