package com.remexs.corp.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import com.remexs.auth.client.interceptor.AuthClientTokenRestInterceptor;
import com.remexs.common.interceptor.GlobalInterceptor;

/**
 * @description MVC视图配置
 *
 * @author remexs
 * @email bigdrone@163.com
 * @date 2017年11月6日 下午5:14:52
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter  {
	/**
	 *  资源过滤例外路径配置
	 * @return
	 */
    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/v2/api-docs",
                "/swagger-resources/**",
                "/cache/**"
        };
        Collections.addAll(list, urls);
        return list;
    }
    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new GlobalInterceptor());
		registry.addInterceptor(new AuthClientTokenRestInterceptor()).excludePathPatterns(getExcludeCommonPathPatterns().toArray(new String[]{}));;
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// REST中根据URL后缀自动判定Content-Type及相应的View
		Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		configurer.ignoreAcceptHeader(true).favorPathExtension(true).mediaTypes(mediaTypes);
	}

}
