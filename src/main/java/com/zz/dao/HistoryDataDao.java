package com.zz.dao;

import com.zz.base.BaseDao;
import com.zz.entity.HistoryData;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/13
 * Time: 13:27
 * Description: No Description
 */
public interface HistoryDataDao extends BaseDao {

    /**
     * 获取池塘当前记录数据
     * @param pondId
     * @return
     */
    HistoryData getCurrentData(Long pondId);

    /**
     * 获取前十记录数据
     * @param pondId
     * @return
     */
    List<HistoryData> getPreviousData(Long pondId);

    /**
     * 获取池塘所有数据
     * @param pondId
     * @return
     */
    List<HistoryData> getAllData(Long pondId);

    Integer insertBatch(List<HistoryData> list);
}
