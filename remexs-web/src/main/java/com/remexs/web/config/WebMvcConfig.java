package com.remexs.web.config;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.remexs.web.interceptor.ResponseInterceptor;

/**
 * @description MVC视图配置
 *
 * @author remexs
 * @email bigdrone@163.com
 * @date 2017年11月6日 下午5:14:52
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter  {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ResponseInterceptor());
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// REST中根据URL后缀自动判定Content-Type及相应的View
		Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		configurer.ignoreAcceptHeader(true).favorPathExtension(true).mediaTypes(mediaTypes);
	}
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		/** 使用阿里 FastJson 作为JSON MessageConverter */
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(
			SerializerFeature.WriteMapNullValue, 		// 保留空的字段
			SerializerFeature.WriteNullStringAsEmpty, 	// String null -> ""
			SerializerFeature.WriteNullNumberAsZero, 	// Number null -> 0
			SerializerFeature.PrettyFormat, 			//
			SerializerFeature.WriteDateUseDateFormat	//
		);
		converter.setFastJsonConfig(config);
		converter.setDefaultCharset(Charset.forName("UTF-8"));
		converters.add(converter);
	}
}
