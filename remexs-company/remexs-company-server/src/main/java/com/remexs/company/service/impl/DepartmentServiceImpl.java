package com.remexs.company.service.impl;

import com.remexs.company.entity.Department;
import com.remexs.company.dao.DepartmentDao;
import com.remexs.company.service.DepartmentService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司部门 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@Service
public class DepartmentServiceImpl extends MybatisServiceImpl<DepartmentDao, Department> implements DepartmentService {

}
