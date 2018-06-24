package com.remexs.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
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
public class SwaggerConfig {
	public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.remexs";
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("remexs 接口文档")
				.description("remexs-cloud 接口文档作者：remexs")
				.termsOfServiceUrl("http://www.baidu.com")
				.version("1.0.RELEASE").build();
	}
    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("REMEXS-CLOUD-API")
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .build()
                .apiInfo(apiInfo());
    }
}
