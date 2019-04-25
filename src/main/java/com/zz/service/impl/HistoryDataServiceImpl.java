package com.zz.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.base.BaseDao;
import com.zz.base.BaseServiceImpl;
import com.zz.convert.PondDataResponseHistoryDataConverter;
import com.zz.dao.FishDao;
import com.zz.dao.HistoryDataDao;
import com.zz.dao.PondDao;
import com.zz.entity.Fish;
import com.zz.entity.HistoryData;
import com.zz.entity.Pond;
import com.zz.service.HistoryDataService;
import com.zz.service.PondService;
import com.zz.util.EWeightConstant;
import com.zz.util.FishPondRuntimeException;
import com.zz.util.GetWeightUtil;
import com.zz.util.ReturnCodeEnum;
import com.zz.vo.response.PondComResponse;
import com.zz.vo.response.PondDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/13
 * Time: 13:30
 * Description: No Description
 */
@Service
public class HistoryDataServiceImpl extends BaseServiceImpl<HistoryData> implements HistoryDataService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HistoryDataDao historyDataDao;
    @Autowired
    private PondDao pondDao;
    @Autowired
    private FishDao fishDao;
    @Autowired
    private PondService pondService;
    @Autowired
    private PondDataResponseHistoryDataConverter pondDataResponseHistoryDataConverter;

    @Override
    protected BaseDao getDao() {
        return historyDataDao;
    }

    @Override
    public List<PondDataResponse> getPondsData() {
        List<PondComResponse> comResponses = pondService.getPondCom();
        List<PondDataResponse> pondDataResponses = new ArrayList<>();
        for (PondComResponse comResponse : comResponses){
            int flag = 0;
            PondDataResponse pondDataResponse = new PondDataResponse();
            String comTemp = comResponse.getComTemperature();
            String comOxy = comResponse.getComOxygen();
            String comPh = comResponse.getComPh();

            //插入池塘id
            pondDataResponse.setPondId(comResponse.getPondId());

            //插入温度
            if (!StringUtils.isEmpty(comTemp)){
                flag++;
                pondDataResponse.setTemperature(getData(comTemp));
            }

            //插入含氧量
            if (!StringUtils.isEmpty(comOxy)){
                flag++;
                pondDataResponse.setOxygen(getData(comOxy));
            }

            //插入ph
            if (!StringUtils.isEmpty(comPh)){
                flag++;
                pondDataResponse.setPh(getData(comPh));
            }

            if (flag != 0){
                pondDataResponses.add(pondDataResponse);
            }
        }
        return pondDataResponses;
    }

    @Override
    public HistoryData getCurrentData(Long pondId) {
        return historyDataDao.getCurrentData(pondId);
    }

    @Override
    public List<HistoryData> getPreviousData(Long pondId) {
        return historyDataDao.getPreviousData(pondId);
    }

    @Override
    public List<HistoryData> getAllData(Long pondId) {
        return historyDataDao.getAllData(pondId);
    }

    @Override
    //@Scheduled(cron="0/5 * *  * * ? ")
    public void recordCurrentData() {
        List<PondDataResponse> pondDataResponses = getPondsData();
        List<HistoryData> historyDataList = pondDataResponseHistoryDataConverter.convert(pondDataResponses, HistoryData.class);
        historyDataDao.insertBatch(historyDataList);
    }

    @Override
    public double getData(String comNum) {
        try{
            if(!EWeightConstant.IS_OPEN_PORT){
                GetWeightUtil.comConfig(comNum);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //全局变量，记录串口数据
        String weight = EWeightConstant.GOOD_WEIGHT;
        if(StringUtils.equals(weight, "0")){
            return Double.parseDouble(weight);
        }
        //截取字符串，获取要取得数据部分
        weight = weight.substring(5,9);
        return Double.parseDouble(weight);
    }

    @Override
    public PageInfo<HistoryData> findPageByPondId(Long pondId, int pageNum, int pageSize) throws FishPondRuntimeException {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<HistoryData> list = this.getAllData(pondId);
            return new PageInfo<>(list);
        }catch (FishPondRuntimeException e){
            this.logger.error("findBy", e);
            throw new FishPondRuntimeException(ReturnCodeEnum.DATE_PARSE_EXCEPTION_ERROR, "数据分析异常");
        }
    }

    @Override
    public String dataAlarm(long pondId) {
        Pond pond = pondDao.get(pondId);
        Long breedFishId = pond.getBreedFishId();
        Fish fish = fishDao.get(breedFishId);
        HistoryData historyData = this.getCurrentData(pondId);
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        //温度
        if (historyData.getTemperature() > fish.getOptimumTemperature() + 1){
            sb.append("温度过高，建议把温度升高。<br/>");
            flag++;
        }else if (historyData.getTemperature() < fish.getOptimumTemperature() - 1){
            sb.append("温度过低，建议把温度降低。<br/>");
            flag++;
        }
        //含氧量
        if (historyData.getOxygen() > fish.getOptimumOxygen() + 0.1){
            sb.append("含氧量过高，建议把含氧量升高。<br/>");
            flag++;
        }else if (historyData.getOxygen() < fish.getOptimumOxygen() - 0.1){
            sb.append("含氧量过低，建议把含氧量降低。<br/>");
            flag++;
        }
        //Ph值
        if (historyData.getPh() > fish.getOptimumPh() + 0.5){
            sb.append("Ph值过高，建议把Ph值升高。<br/>");
            flag++;
        }else if (historyData.getPh() < fish.getOptimumPh() - 0.5){
            sb.append("Ph值过低，建议把Ph值降低。<br/>");
            flag++;
        }

        if (flag == 0){
            sb.append("鱼塘环境完美！请保持。");
        }
        return StringUtils.isEmpty(sb) ? null : sb.toString();
    }

    public static void main(String args[]){
        String str = "aa\r\nbb";
        System.out.println(str);
    }
}
