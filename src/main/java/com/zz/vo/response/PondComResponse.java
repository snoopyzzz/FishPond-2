package com.zz.vo.response;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/21
 * Time: 15:04
 * Description: No Description
 */
public class PondComResponse {
    private Long pondId;
    private String comTemperature;
    private String comOxygen;
    private String comPh;

    public Long getPondId() {
        return pondId;
    }

    public void setPondId(Long pondId) {
        this.pondId = pondId;
    }

    public String getComTemperature() {
        return comTemperature;
    }

    public void setComTemperature(String comTemperature) {
        this.comTemperature = comTemperature;
    }

    public String getComOxygen() {
        return comOxygen;
    }

    public void setComOxygen(String comOxygen) {
        this.comOxygen = comOxygen;
    }

    public String getComPh() {
        return comPh;
    }

    public void setComPh(String comPh) {
        this.comPh = comPh;
    }
}
