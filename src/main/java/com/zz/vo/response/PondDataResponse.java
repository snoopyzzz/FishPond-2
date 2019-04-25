package com.zz.vo.response;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/21
 * Time: 15:03
 * Description: No Description
 */
public class PondDataResponse {
    private Long pondId;
    private double temperature;
    private double oxygen;
    private double ph;

    public Long getPondId() {
        return pondId;
    }

    public void setPondId(Long pondId) {
        this.pondId = pondId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getOxygen() {
        return oxygen;
    }

    public void setOxygen(double oxygen) {
        this.oxygen = oxygen;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }
}
