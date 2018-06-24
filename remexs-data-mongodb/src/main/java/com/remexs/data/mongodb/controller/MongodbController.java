package com.remexs.data.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.common.vo.PageVO;
import com.remexs.data.mongodb.entity.MongoEntity;
import com.remexs.data.mongodb.service.MongoService;

/**
 * mongo db 前端控制器
 * @author remexs
 *
 * @param <BaseService>
 * @param <Entity>
 */
public class MongodbController  <BaseService extends MongoService<Entity>, Entity extends MongoEntity>{
	@Autowired
	protected BaseService baseService;

	/**
	 * 新增
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
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
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<Entity>> list(HashDto paramsDto, @RequestParam(required = false) HashDto sortsDto) {
		List<Entity> list = baseService.list(paramsDto, sortsDto);
		return ResultUtils.ok(list);
	}

	/**
	 * 
	 * @param paramsDto
	 * @param sortsDto
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Result<PageVO<Entity>> page(@RequestParam HashDto paramsDto,
			@RequestParam(required = false) HashDto sortsDto) {
		PageVO<Entity> pageVO = new PageVO<>();
		baseService.page(pageVO);
		return ResultUtils.ok(pageVO);
	}
}
