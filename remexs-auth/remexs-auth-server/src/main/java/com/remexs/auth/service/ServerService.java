package com.remexs.auth.service;


import com.remexs.auth.entity.Server;
import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author remexs
 * @since 2018-07-03
 */
public interface ServerService extends MybatisService<Server> {
	
	/**
	 * 根据服务代码 验证服务是否存在
	 * @param code
	 * @return
	 */
	public Boolean existBy(String code);
	/**
	 * 根据服务代码 获得服务详情
	 * @param code
	 * @return
	 */
	public Server getBy(String code);
	/**
	 * 根据编码获得服务密钥
	 * @param id
	 * @return
	 */
	public String getSecretBy(String id);
	
	/**
	 * 根据编码获得服务密钥
	 * @param id
	 * @return
	 */
	public String connect(String code,String secret);
	/**
	 * 根据服务代码和接口代码查询接口是否分配了权限
	 * @param serverCode
	 * @param resourceCode
	 * @return
	 */
	public boolean hasPromiseBy(String serverCode,String resourceCode);
}
