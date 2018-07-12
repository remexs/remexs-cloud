package com.remexs.corp.service.impl;

import com.remexs.corp.entity.Company;
import com.remexs.corp.dao.CompanyDao;
import com.remexs.corp.service.CompanyService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@Service
public class CompanyServiceImpl extends MybatisServiceImpl<CompanyDao, Company> implements CompanyService {

}
