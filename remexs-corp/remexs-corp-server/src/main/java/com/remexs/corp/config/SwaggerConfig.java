package com.remexs.corp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description swagger API文档配置
 * 
 * @author remexs
 * @email bigdrone@163.com
 * @date 2017年11月6日 下午5:14:52
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("true")
public class SwaggerConfig implements EnvironmentAware{
	
	private String authClientUserTokenHeader;
	
	public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.remexs.company.controller";
    private ApiInfo apiInfo() {
    	
		return new ApiInfoBuilder()
				.title("remexs-company 接口文档")
				.description("remexs-cloud 接口文档作者：remexs")
				.termsOfServiceUrl("http://www.baidu.com")
				.version("1.0.RELEASE").build();
	}
    @Bean
    public Docket api(){
    	ParameterBuilder tokenPar = new ParameterBuilder();
    	List<Parameter> pars = new ArrayList<Parameter>();
    	tokenPar.name(authClientUserTokenHeader).description("用户令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
    	pars.add(tokenPar.build());
    	
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("REMEXS-COMPANY-API")
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }
	@Override
	public void setEnvironment(Environment env) {
		this.authClientUserTokenHeader=env.getProperty("auth.client.user-token-header", "user-token");
		this.authClientUserTokenHeader=env.getProperty("auth.client.client-token-header", "client-token");
	}
}
