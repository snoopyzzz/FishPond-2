package com.zz.controller;

import com.zz.base.BaseController;
import com.zz.entity.HistoryData;
import com.zz.service.HistoryDataService;
import com.zz.util.Jo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/13
 * Time: 13:33
 * Description: No Description
 */
@Controller
@RequestMapping(value = "historyData")
public class HistoryDataController extends BaseController<HistoryData, HistoryDataService> {

    @Autowired
    private HistoryDataService historyDataService;

    @Override
    protected HistoryDataService getService() {
        return historyDataService;
    }

    @RequestMapping
    public String toIndex(){
        return "history/index";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllData/{pondId}", method = RequestMethod.GET)
    public Jo getAllData(@PathVariable Long pondId){
        return new Jo().success().sendData(getService().getAllData(pondId));
    }


}
