package com.remexs.auth.client.listeners;

import java.util.List;

import org.springframework.context.ApplicationListener;

import com.remexs.auth.client.event.AuthRemoteEvent;

/**
 * 鉴权客户端 事件监听
 * @author remexs
 *
 */
public class AuthClientListener implements ApplicationListener<AuthRemoteEvent> {

	private List<String> allowedClient;
	private String clientToken;

	@Override
	public void onApplicationEvent(AuthRemoteEvent authRemoteEvent) {
		this.allowedClient = authRemoteEvent.getAllowedClient();
	}

}
