package com.remexs.corp.service.impl;

import com.remexs.corp.entity.CorpDept;
import com.remexs.corp.dao.CorpDeptDao;
import com.remexs.corp.service.CorpDeptService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司部门 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Service
public class CorpDeptServiceImpl extends MybatisServiceImpl<CorpDeptDao, CorpDept> implements CorpDeptService {

}
