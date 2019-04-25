package com.zz.dao;

import com.zz.base.BaseDao;
import com.zz.entity.Pond;

import java.util.List;

/** 池塘Dao
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/10
 * Time: 19:58
 * Description: No Description
 */
public interface PondDao extends BaseDao {

    List<Pond> getPondsCom();
}
