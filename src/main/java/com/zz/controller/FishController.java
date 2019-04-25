package com.zz.controller;

import com.zz.base.BaseController;
import com.zz.entity.Fish;
import com.zz.service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/10
 * Time: 10:52
 * Description: No Description
 */
@Controller
@RequestMapping(value = "fish")
public class FishController extends BaseController<Fish, FishService> {

    @Autowired
    private FishService fishService;

    @Override
    protected FishService getService() {
        return fishService;
    }

    @RequestMapping
    public String toIndex(){
        return "fish/index";
    }

    @RequestMapping("add")
    public String toAdd(){
        return "fish/add";
    }

    @RequestMapping("modify")
    public String toModify(){
        return "fish/modify";
    }

    @RequestMapping("get")
    public String toGet(){
        return "fish/get";
    }

}
