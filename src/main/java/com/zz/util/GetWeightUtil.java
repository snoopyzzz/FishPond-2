package com.zz.util;

import com.zz.transfer.CommDataObserver;
import com.zz.transfer.SerialReader;
import gnu.io.SerialPort;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/19
 * Time: 14:39
 * Description: No Description
 */


public class GetWeightUtil {

    /**
     * 串口设置， 初始化端口数据
     * @param com 端口
     */
    public static void comConfig(String com) {
        //存储端口数据
        HashMap<String, Comparable> params = new HashMap<>();
        // 端口名称
        params.put(SerialReader.PARAMS_PORT, com);
        // 波特率
        params.put(SerialReader.PARAMS_RATE, 115200);
        // 设备超时时间 1秒
        params.put(SerialReader.PARAMS_TIMEOUT, 1000);
        // 端口数据准备时间 1秒
        params.put(SerialReader.PARAMS_DELAY, 200);
        // 数据位
        params.put(SerialReader.PARAMS_DATABITS, SerialPort.DATABITS_8);
        // 停止位
        params.put(SerialReader.PARAMS_STOPBITS, SerialPort.STOPBITS_1);
        // 无奇偶校验
        params.put(SerialReader.PARAMS_PARITY, SerialPort.PARITY_NONE);
        //创建被观察者
        SerialReader serialReader = new SerialReader(params);
        //创建观察者
        CommDataObserver obServer = new CommDataObserver();
        //添加一个观察者，加入观察
        serialReader.addObserver(obServer);

        //TODO 调试用，输出到控制台 显示所有串口号
        SerialReader.listPorts();
    }

    public static void main(String[] args) throws Exception{
        comConfig("COM4");
        while(true){
            Thread.sleep(1000);
            System.out.println(EWeightConstant.GOOD_WEIGHT);
            System.out.println(EWeightConstant.GOOD_WEIGHT.substring(5,9));
        }

    }
}
