package com.zz.entity;

import com.google.common.base.MoreObjects;
import com.zz.base.BaseEntity;

import java.sql.Timestamp;


/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2019/3/11
 * Time: 10:12
 * Description: No Description
 */
public class HistoryData extends BaseEntity {

    /**
     * 池塘Id
     */
    private Long pondId;
    /**
     * 温度
     */
    private double temperature;
    /**
     * 含氧量
     */
    private double oxygen;
    /**
     * PH值
     */
    private double ph;
    /**
     * 记录时间
     */
    private Timestamp recordTime;
    /**
     * 备注
     */
    private String remark;

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

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pondId",pondId)
                .add("temperature",temperature)
                .add("oxygen",oxygen)
                .add("ph",ph)
                .add("recordTime",recordTime)
                .add("remark",remark)
                .toString();
    }
}
