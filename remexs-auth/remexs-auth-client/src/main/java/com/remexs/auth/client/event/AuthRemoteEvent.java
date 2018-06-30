package com.remexs.auth.client.event;

import java.util.List;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)

public class AuthRemoteEvent extends RemoteApplicationEvent{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4820310517969599475L;
	
	private List<String> allowedClient;

    //jackson序列化反序列化必须有无参构造函数
    public AuthRemoteEvent() {
    
    }

    public AuthRemoteEvent(Object source, String originService, String destinationService, List<String> allowedClient) {
       
        super(source, originService, destinationService);
        this.allowedClient = allowedClient;
    }

}
