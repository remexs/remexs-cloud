package com.remexs.corp.service.impl;

import com.remexs.corp.entity.CorpStaff;
import com.remexs.corp.dao.CorpStaffDao;
import com.remexs.corp.service.CorpStaffService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司用户 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Service
public class CorpStaffServiceImpl extends MybatisServiceImpl<CorpStaffDao, CorpStaff> implements CorpStaffService {

}
