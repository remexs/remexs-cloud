package com.remexs.auth.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remexs.common.dto.Dto;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;


@FeignClient(value = "remexs-auth")
public interface AuthServerService {

	/**
	 * 获得鉴权中心鉴权配置信息
	 * @return
	 */
	@RequestMapping(value = "/server/getServerConfig", method = RequestMethod.GET)
	Result<HashDto> getServerConfig();
}
