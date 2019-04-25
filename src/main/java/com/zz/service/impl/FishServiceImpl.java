package com.zz.service.impl;

import com.zz.base.BaseDao;
import com.zz.base.BaseServiceImpl;
import com.zz.dao.FishDao;
import com.zz.entity.Fish;
import com.zz.service.FishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/10
 * Time: 10:48
 * Description: No Description
 */
@Service
public class FishServiceImpl extends BaseServiceImpl<Fish> implements FishService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FishDao fishDao;

    @Override
    protected BaseDao getDao() {
        return fishDao;
    }



}
