package com.zz.dao;

import com.zz.base.BaseDao;
import com.zz.entity.User;
import com.zz.util.FishPondRuntimeException;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/8
 * Time: 17:02
 * Description: No Description
 */
public interface UserDao extends BaseDao {

    /**
     * 通过名字查找
     * @param name
     * @return
     * @throws FishPondRuntimeException
     */
    User findByUsername(String name) throws FishPondRuntimeException;

}
