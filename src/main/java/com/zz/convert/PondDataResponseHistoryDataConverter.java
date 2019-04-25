package com.zz.convert;

import com.zz.base.BaseConverter;
import com.zz.entity.HistoryData;
import com.zz.vo.response.PondDataResponse;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/24
 * Time: 17:01
 * Description: No Description
 */
@Component
public class PondDataResponseHistoryDataConverter extends BaseConverter<PondDataResponse, HistoryData> {

    @Override
    protected void convert(PondDataResponse pondDataResponse, HistoryData historyData){
        super.convert(pondDataResponse, historyData);
    }
}
