package com.remexs.corp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.corp.entity.Corp;
import com.remexs.corp.service.CorpService;
import com.remexs.data.mybatis.controller.MybatisController;


/**
 * <p>
 * 公司 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@RestController
@RequestMapping("/corp")
public class CorpController extends MybatisController<CorpService, Corp>{
	@Autowired
	CorpService corpService;
}

