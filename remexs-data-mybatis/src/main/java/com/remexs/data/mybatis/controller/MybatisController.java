package com.remexs.data.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.data.mybatis.entity.MybatisEntity;
import com.remexs.data.mybatis.service.MybatisService;

/**
 * 
 * @author remexs
 *
 */
public class MybatisController<BaseService extends MybatisService<Entity>, Entity extends MybatisEntity> extends BaseController {

	@Autowired
	protected BaseService baseService;

	/**
	 * 新增
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiMethodFilter(name = "新增", code = "add", method = "POST", path = "")
	public Result<String> add(@RequestBody Entity entity) {
		baseService.insert(entity);
		return ResultUtils.ok(entity.getId());
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiMethodFilter(name = "修改", code = "update", method = "PUT", path = "{*}")
	@ResponseBody
	public Result<Void> update(@RequestBody Entity entity) {
		baseService.updateById(entity);
		return ResultUtils.ok();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiMethodFilter(name = "删除", code = "remove", method = "DELETE", path = "{*}")
	@ResponseBody
	public Result<Void> remove(@PathVariable String id) {
		baseService.deleteById(id);
		return ResultUtils.ok();
	}

	/**
	 * 根据编码获得实体记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiMethodFilter(name = "查看", code = "get", method = "GET", path = "/{*}")
	@ResponseBody
	public Result<Entity> get(@PathVariable String id) {
		Entity entity = baseService.selectById(id);
		return ResultUtils.ok(entity);
	}

	/**
	 * 
	 * @param paramsDto
	 * @param sortsDto
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ApiMethodFilter(name = "列表", code = "list", method = "PATCH", path = "")
	@ResponseBody
	public Result<List<Entity>> list(@RequestBody(required = false) HashDto paramsDto) {
		List<Entity> list = baseService.list(paramsDto, null);
		return ResultUtils.ok(list);
	}

}
