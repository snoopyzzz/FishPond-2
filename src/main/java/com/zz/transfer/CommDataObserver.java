package com.zz.transfer;

import com.zz.util.EWeightConstant;

import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/19
 * Time: 14:34
 * Description: No Description
 */
public class CommDataObserver implements Observer {
    private String name;

    public CommDataObserver() {
    }

    public CommDataObserver(String name) {
        this.name = name;
    }
    /**
     * 监控串口，获取串口数据
     */
    public void update(Observable o, Object arg) {
        //获取串口返回数据
        String weight = new String((byte[]) arg).trim();
        //将获取到的数据，赋值到全局变量里，以便可以调用该数据
        EWeightConstant.GOOD_WEIGHT = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
