package com.zz.service.impl;

import com.zz.base.BaseController;
import com.zz.base.BaseConverter;
import com.zz.base.BaseDao;
import com.zz.base.BaseServiceImpl;
import com.zz.convert.PondPondComResponseConverter;
import com.zz.dao.PondDao;
import com.zz.entity.Pond;
import com.zz.service.PondService;
import com.zz.vo.response.PondComResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/10
 * Time: 20:44
 * Description: No Description
 */
@Service
public class PondServiceImpl extends BaseServiceImpl<Pond> implements PondService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PondDao pondDao;
    @Autowired
    private PondPondComResponseConverter pondPondComResponseConverter;


    @Override
    protected PondDao getDao() {
        return pondDao;
    }

    @Override
    public List<PondComResponse> getPondCom() {
        List<Pond> pondList = getDao().getPondsCom();
        return pondPondComResponseConverter.convert(pondList, PondComResponse.class);
    }
}
