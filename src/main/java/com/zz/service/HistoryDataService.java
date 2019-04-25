package com.zz.service;

import com.github.pagehelper.PageInfo;
import com.zz.base.BaseService;
import com.zz.entity.HistoryData;
import com.zz.vo.response.PondDataResponse;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/13
 * Time: 13:25
 * Description: No Description
 */
public interface HistoryDataService extends BaseService<HistoryData> {

    /**
     * 获取现有池塘所有环境数据
     * @return
     */
    List<PondDataResponse> getPondsData();

    /**
     * 根据池塘id，获取当前时刻的环境数据
     * @param pondId
     * @return
     */
    HistoryData getCurrentData(Long pondId);

    /**
     * 根据池塘id，获取前十条的环境数据
     * @param pondId
     * @return
     */
    List<HistoryData> getPreviousData(Long pondId);

    /**
     * 根据池塘id，获取所有时刻的环境数据
     * @param pondId
     * @return
     */
    List<HistoryData> getAllData(Long pondId);

    /**
     * 记录环境数据
     */
    void recordCurrentData();

    /**
     * 获取该串口的数据
     * @param comNum
     * @return
     */
    double getData(String comNum);

    /**
     * 根据池塘id分页查找
     * @param pageNum
     * @param pageSize
     * @param pondId
     * @return
     */
    PageInfo<HistoryData> findPageByPondId(Long pondId, int pageNum, int pageSize);

    /**
     * 监测养鱼池
     * @param pondId
     * @return
     */
    String dataAlarm(long pondId);
}
