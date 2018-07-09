package com.remexs.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;

import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.utils.RsaUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtils {

	/**
	 * 密钥加密token
	 *
	 * @param info 待加密的信息
	 * @param priKeyPath 私钥路径
	 * @param expire 过期时间/秒
	 * @return
	 * @throws Exception
	 */
	public static String generateToken(Dto info, String priKeyPath, int expire) throws Exception {
		InputStream inputStream = null;
		String compactJws = null;
		
		try {
			
			inputStream = FileUtils.openInputStream(new File(priKeyPath));
			 JwtBuilder builder = Jwts.builder();
			 info.keySet().forEach(key->{
		 		if("subject".equals(key)) {
		 			builder.setSubject(info.getString("subject"));
		 		}else {
		 			builder.claim(key, info.get(key));
		 		}
			});
			 
		 	builder.setExpiration(DateTime.now().plusSeconds(expire).toDate());
		 	compactJws=builder.signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(inputStream)).compact();
		 	
		} catch (IOException e) {
			
		} finally {
			inputStream.close();
		}
		return compactJws;
	}

	/**
	 * 密钥加密token
	 *
	 * @param info 待加密的信息
	 * @param priKeyPath 私钥字节流
	 * @param expire 过期时间/秒
	 * @return
	 * @throws Exception
	 */
	public static String generateToken(Dto info, byte priKey[], int expire) throws Exception {
		
		JwtBuilder builder = Jwts.builder();
		
		info.keySet().forEach(key->{
	 		if("subject".equals(key)) {
	 			builder.setSubject(info.getString("subject"));
	 		}else {
	 			builder.claim(key, info.get(key));
	 		}
		});
		
		builder.setExpiration(DateTime.now().plusSeconds(expire).toDate());
		
		return builder.signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(priKey)).compact();
	}

	/**
	 * 公钥解析token
	 *
	 * @param token token字符串
	 * @param pubKeyPath 公钥路径
	 * @return
	 * @throws Exception
	 */
	public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
		InputStream inputStream = null;
		Jws<Claims> claimsJws= null;
		try {
			inputStream=FileUtils.openInputStream(new File(pubKeyPath));
			 claimsJws = Jwts.parser().setSigningKey(RsaUtils.getPublicKey(inputStream)).parseClaimsJws(token);
		}catch(IOException e) {
			
		}finally {
			inputStream.close();
		}
		return claimsJws;
		
	}

	/**
	 * 公钥解析token
	 *
	 * @param token token字符串
	 * @param pubKeyPath 公钥字节
	 * @return
	 * @throws Exception
	 */
	public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(RsaUtils.getPublicKey(pubKey)).parseClaimsJws(token);
		return claimsJws;
	}

	/**
	 * 获取token中的用户信息
	 *
	 * @param token token字符串
	 * @param pubKeyPath 公钥文件路径
	 * @return 解密后信息
	 * @throws Exception
	 */
	public static Dto getInfoFromToken(String token, String pubKeyPath) throws Exception {
		Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
		Claims body = claimsJws.getBody();
		Dto returnDto = Dtos.newDto();
		body.keySet().forEach(key->{
			returnDto.put(key, body.get(key));
		});
		
		return returnDto;
	}

	/**
	 * 获取token中的用户信息
	 *
	 * @param token token字符串
	 * @param pubKeyPath 公钥字节流
	 * @return 解密后信息
	 * @throws Exception
	 */
	public static Dto getInfoFromToken(String token, byte[] pubKey) throws Exception {
		
		Jws<Claims> claimsJws = parserToken(token, pubKey);
		Claims body = claimsJws.getBody();
		Dto returnDto = Dtos.newDto();
		body.keySet().forEach(key->{
			returnDto.put(key, body.get(key));
		});
		return returnDto;
	}

}
