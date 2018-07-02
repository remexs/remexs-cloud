package com.remexs.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.common.utils.RsaUtils;
import com.remexs.common.utils.SpringUtils;
import com.remexs.data.mybatis.controller.BaseController;

import io.swagger.annotations.Api;

/**
 * 服务前段控制器
 * 
 * @author remexs
 *
 */
@Api
@RestController
@RequestMapping("/server")
@ApiFilter(name = "用户接口", code = "server", path = "/server")
public class AuthServerController extends BaseController {
	Logger logger=LoggerFactory.getLogger(AuthServerController.class);


	@RequestMapping(value = "/getServerConfig", method = RequestMethod.GET)
	@ApiMethodFilter(name = "获得鉴权中心鉴权配置信息", code = "getServerConfig", method = "GET", path = "/getServerConfig", userToken = false, clientToken = false)
	public Result<HashDto> getServerConfig() {
		logger.info("获得鉴权中心鉴权配置信息");
		AuthServerConfiguration config=SpringUtils.getBean(AuthServerConfiguration.class);
		logger.info(config.toString());
		
		HashDto configDto=new HashDto();
		configDto.put("serverMode", config.getServerMode());
		configDto.put("serverName", config.getServerName());
		configDto.put("serverUserTokenEnable", config.getUserTokenEnable());
		configDto.put("serverUserTokenHeader", config.getUserTokenHeader());
		configDto.put("serverUserTokenPubKey", RsaUtils.toHexString(config.getUserTokenPubKey()));
		configDto.put("serverClientTokenEnable", config.getClientTokenEnable());
		configDto.put("serverClientTokenHeader", config.getClientTokenHeader());
		configDto.put("serverClientTokenPubKey",  RsaUtils.toHexString(config.getClientTokenPubKey()));
		return ResultUtils.ok(configDto);
	}
}
