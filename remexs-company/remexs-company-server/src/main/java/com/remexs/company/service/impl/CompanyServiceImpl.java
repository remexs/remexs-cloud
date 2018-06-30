package com.remexs.company.service.impl;

import com.remexs.company.entity.Company;
import com.remexs.company.dao.CompanyDao;
import com.remexs.company.service.CompanyService;
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
