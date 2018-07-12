package com.remexs.corp.service.impl;

import com.remexs.corp.entity.Employee;
import com.remexs.corp.dao.EmployeeDao;
import com.remexs.corp.service.EmployeeService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司用户 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@Service
public class EmployeeServiceImpl extends MybatisServiceImpl<EmployeeDao, Employee> implements EmployeeService {

}
