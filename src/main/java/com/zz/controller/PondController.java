package com.zz.controller;

import com.zz.base.BaseController;
import com.zz.entity.Pond;
import com.zz.service.HistoryDataService;
import com.zz.service.PondService;
import com.zz.util.Jo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/10
 * Time: 20:48
 * Description: No Description
 */
@Controller
@RequestMapping(value = "pond")
public class PondController extends BaseController<Pond, PondService> {

    @Autowired
    private PondService pondService;
    @Autowired
    private HistoryDataService historyDataService;

    protected PondService getService(){
        return pondService;
    }

    @RequestMapping
    public String toIndex(){
        return "pond/index";
    }

    @RequestMapping("add")
    public String toAdd(){
        return "pond/add";
    }

    @RequestMapping("modify")
    public String toModify(){
        return "pond/modify";
    }

    @RequestMapping("get")
    public String toGet(){
        return "pond/get";
    }

    @ResponseBody
    @RequestMapping(value = "getCom", method = RequestMethod.GET)
    public Jo getCom(){
        return new Jo().success().sendData(getService().getPondCom());
    }

    @ResponseBody
    @RequestMapping(value = "findPageByPondId", method = RequestMethod.GET)
    public Jo findPage(Long pondId,
                       @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws Exception{
        return new Jo().success().sendData(historyDataService.findPageByPondId(pondId, pageNum, pageSize));
    }

    /**
     * 获取当前数据
     * @param pondId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCurrentData/{pondId}", method = RequestMethod.GET)
    public Jo getCurrentData(@PathVariable Long pondId){
        return new Jo().success().sendData(historyDataService.getCurrentData(pondId));
    }

    /**
     * 获取前10条数据
     * @param pondId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPreviousData/{pondId}", method = RequestMethod.GET)
    public Jo getPreviousData(@PathVariable Long pondId){
        return new Jo().success().sendData(historyDataService.getPreviousData(pondId));
    }

    /**
     * 告警
     * @param pondId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dataAlarm/{pondId}", method = RequestMethod.GET)
    public Jo dataAlarm(@PathVariable Long pondId){
        return new Jo().success().sendData(historyDataService.dataAlarm(pondId));
    }
}
