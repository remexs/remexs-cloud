package com.remexs.auth.controller;

import java.rmi.ServerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.auth.constants.AuthConstants;
import com.remexs.auth.entity.Server;
import com.remexs.auth.service.ServerResourceService;
import com.remexs.auth.service.ServerService;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.common.utils.RsaUtils;
import com.remexs.common.utils.SpringUtils;
import com.remexs.data.mybatis.controller.MybatisController;

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
@ApiFilter(name = "服务接口", code = "server", path = "/server")
public class ServerController extends MybatisController<ServerService, Server> {
	Logger logger = LoggerFactory.getLogger(ServerController.class);

	/**
	 * 客户端自动注册(开发者调用)
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiMethodFilter(name = "注册服务", code = "add", method = "POST", path = "/add", userTokenFilter = false)
	public Result<String> add(@RequestBody Server server) {
		baseService.insertOrUpdate(server);
		return ResultUtils.ok(server.getId());
	}

	/**
	 * 客户端连接服务
	 * 
	 * @throws ServerException
	 */
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	@ApiMethodFilter(name = "客户端连接服务", code = "connect", method = "POST", path = "/connect", userTokenFilter = false)
	public Result<String> connect(@RequestParam String code, @RequestParam String secret) {
		String token = baseService.connect(code, secret);
		return ResultUtils.ok(token);
	}

	/**
	 * 获得服务配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getConfig", method = RequestMethod.GET)
	@ApiMethodFilter(name = "获得鉴权中心鉴权配置信息", code = "getConfig", method = "GET", path = "/getConfig",  userTokenFilter = false)
	public Result<HashDto> getAuthServerConfig() {
		logger.info("获得鉴权中心鉴权配置信息。。。");
		AuthServerConfiguration config = SpringUtils.getBean(AuthServerConfiguration.class);
		HashDto configDto = new HashDto();
		configDto.put(AuthConstants.SERVER_CODE, config.getServerCode());
		configDto.put(AuthConstants.CLIENT_TOKEN_HEADER, config.getClientTokenHeader());
		configDto.put(AuthConstants.CLIENT_TOKEN_EXPIRE, config.getClientTokenExpire());
		configDto.put(AuthConstants.CLIENT_TOKEN_PUBKEY, RsaUtils.toHexString(config.getClientTokenPubKey()));
		configDto.put(AuthConstants.USER_TOKEN_HEADER, config.getUserTokenHeader());
		configDto.put(AuthConstants.USER_TOKEN_EXPIRE, config.getClientTokenExpire());
		configDto.put(AuthConstants.USER_TOKEN_PUBKEY, RsaUtils.toHexString(config.getUserTokenPubKey()));
		logger.info(configDto.toJson());
		return ResultUtils.ok(configDto);
	}

	/**
	 * 根据服务编码获得服务密钥（需要服务管理员权限访问）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSecret", method = RequestMethod.GET)
	@ApiMethodFilter(name = "根据服务编码获得服务密钥", code = "getSecret", method = "GET", path = "/getSecret", userTokenFilter = false)
	public Result<String> getSercret(@RequestParam String id) {
		logger.info("鉴权中心注册服务。。。");
		return ResultUtils.ok(baseService.getSecretBy(id));
	}
	/**
	 * 根据服务编码和接口编码查询制定服务是否有权
	 * 
	 * @return
	 */
	@RequestMapping(value = "/hasPromiseBy", method = RequestMethod.POST)
	@ApiMethodFilter(name = "判断服务是否有权限访问指定方法", code = "hasPromiseBy", method = "POST", path = "/hasPromiseBy", userTokenFilter = false)
	public Result<Boolean> hasPromiseBy(@RequestParam String serverCode, @RequestParam String resourceCode) {
		return ResultUtils.ok(baseService.hasPromiseBy(serverCode, resourceCode));
	}
}

