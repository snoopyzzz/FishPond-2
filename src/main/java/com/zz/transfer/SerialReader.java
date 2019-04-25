package com.zz.transfer;

import com.zz.util.EWeightConstant;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/19
 * Time: 14:32
 * Description: No Description
 */


public class SerialReader extends Observable implements Runnable, SerialPortEventListener {
    static CommPortIdentifier portId;
    int delayRead = 200;
    int numBytes; // buffer中的实际数据字节数
    private static byte[] readBuffer = new byte[4096]; // 4k的buffer空间,缓存串口读入的数据
    static Enumeration portList;
    InputStream inputStream;
    SerialPort serialPort;
    //声明观察者时，被指定的参数信息
    HashMap serialParams;
    // 端口读入数据事件触发后,等待n毫秒后再读取,以便让数据一次性读完
    public static final String PARAMS_DELAY = "delay read"; // 延时等待端口数据准备的时间
    public static final String PARAMS_TIMEOUT = "timeout"; // 超时时间
    public static final String PARAMS_PORT = "port name"; // 端口名称
    public static final String PARAMS_DATABITS = "data bits"; // 数据位
    public static final String PARAMS_STOPBITS = "stop bits"; // 停止位
    public static final String PARAMS_PARITY = "parity"; // 奇偶校验
    public static final String PARAMS_RATE = "rate"; // 波特率

    public SerialReader() {
    }

    /**
     * 初始化端口操作的参数.
     *
     * @see
     */
    public SerialReader(HashMap params) {
        serialParams = params;
        init();
    }

    private void init() {
        try {
            // 参数初始化
            int timeout = Integer.parseInt(serialParams.get(PARAMS_TIMEOUT).toString());
            int rate = Integer.parseInt(serialParams.get(PARAMS_RATE).toString());
            int dataBits = Integer.parseInt(serialParams.get(PARAMS_DATABITS).toString());
            int stopBits = Integer.parseInt(serialParams.get(PARAMS_STOPBITS).toString());
            int parity = Integer.parseInt(serialParams.get(PARAMS_PARITY).toString());
            delayRead = Integer.parseInt(serialParams.get(PARAMS_DELAY).toString());
            String port = serialParams.get(PARAMS_PORT).toString();
            // 打开端口
            portId = CommPortIdentifier.getPortIdentifier(port);
            EWeightConstant.IS_OPEN_PORT = true;
            serialPort = (SerialPort) portId.open("SerialReader", timeout);
            inputStream = serialPort.getInputStream();
            serialPort.addEventListener(this);// 注册串口监听
            serialPort.notifyOnDataAvailable(true);// 数据可用
            serialPort.setSerialPortParams(rate, dataBits, stopBits, parity);// 设置参数
        } catch (PortInUseException e) {
            // System.out.println("串口已经被占用!");
            e.printStackTrace();
        } catch (TooManyListenersException e) {
            // System.out.println("串口监听者过多!");
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            // System.out.println("串口操作命令不支持!");
            e.printStackTrace();
        } catch (NoSuchPortException e) {
            // System.out.println("串口不存在!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread readThread = new Thread(this);
        readThread.start();
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void serialEvent(SerialPortEvent event) {
        try {
            // 等待1秒钟让串口把数据全部接收后在处理
            Thread.sleep(delayRead);
            // System.out.print("serialEvent[" + event.getEventType() + "] ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (event.getEventType()) {
            case SerialPortEvent.BI: // 10
            case SerialPortEvent.OE: // 7
            case SerialPortEvent.FE: // 9
            case SerialPortEvent.PE: // 8
            case SerialPortEvent.CD: // 6
            case SerialPortEvent.CTS: // 3
            case SerialPortEvent.DSR: // 4
            case SerialPortEvent.RI: // 5
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2
                break;
            case SerialPortEvent.DATA_AVAILABLE: // 1
                try {
                    // 多次读取,将所有数据读入
                    while (inputStream.available() > 0) {
                        numBytes = inputStream.read(readBuffer);
                    }
                    // numBytes = inputStream.read(readBuffer);
                    changeMessage(readBuffer, numBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    // 通过observer pattern将收到的数据发送给observer
    // 将buffer中的空字节删除后再发送更新消息,通知观察者
    private String temp1 = "";

    public void changeMessage(byte[] message, int length) {
        setChanged();
        byte[] temp = new byte[length];
        System.arraycopy(message, 0, temp, 0, length);
        // 判断数据是否稳定，稳定则只监听不执行更新数据
        if (!"".equals(temp1)) {
            if (!temp1.equals(new String(temp).trim())) {
                temp1 = new String(temp).trim();
                notifyObservers(temp);
                // System.out.println("msg[" + numBytes + "]: [" + new
                // String(temp).trim() +"]");
            }
        } else {
            temp1 = new String(temp).trim();
            notifyObservers(temp);
        }
    }
    /**
     * 调试用，获取所有的串口
     */
    public static void listPorts() {
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = (CommPortIdentifier) portEnum.nextElement();
            System.out.println("串口名称："+portIdentifier.getName() + " - " +"串口类型："+getPortTypeName(portIdentifier.getPortType()));
        }
    }

    /**
     * 读取所有串口名字
     */
    public static String getPortName() {
        CommPortIdentifier portId;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();
        while (en.hasMoreElements()) {
            portId = (CommPortIdentifier) en.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                return portId.getName();
            }
        }
        return "";
    }

    /**
     * 读取所有串口名字
     */
    public static void closePort() {
        CommPortIdentifier portId;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();
        while (en.hasMoreElements()) {
            portId = (CommPortIdentifier) en.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {

            }
        }
    }

    static String getPortTypeName(int portType) {
        switch (portType) {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }

    public static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
                case CommPortIdentifier.PORT_SERIAL:
                    try {
                        CommPort thePort = com.open("CommUtil", 50);
                        thePort.close();
                        h.add(com);
                    } catch (PortInUseException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
        return h;
    }
}
