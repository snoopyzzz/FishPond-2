package com.zz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/12
 * Time: 10:41
 * Description: No Description
 */
@Controller
@RequestMapping(value = "index")
public class IndexController {

    @RequestMapping
    public String toIndex(){
        return "index";
    }

    @RequestMapping("test")
    public String myTest(){
        return "test";
    }

    @RequestMapping("ti")
    public String myTestIndex(){
        return "ti";
    }
}
