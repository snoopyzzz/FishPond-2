package com.zz.entity;

import com.google.common.base.MoreObjects;
import com.zz.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/7
 * Time: 23:28
 * Description: No Description
 */
public class Fish extends BaseEntity {

    @NotNull(message = "鱼名称不能为空")
    @Length(min = 1, max = 64, message = "鱼名称长度必须在1~64字符之间")
    private String name;

    @NotNull(message = "鱼备注不能为空")
    @Length(min = 1, max = 500, message = "鱼备注长度必须在1~500字符之间")
    private String remark;

    /**
     * 最适温度
     */
    @NotNull(message = "最适温度不能为空")
    private double optimumTemperature;
    /**
     * 最适含氧量
     */
    @NotNull(message = "最适含氧量不能为空")
    private double optimumOxygen;
    /**
     * 最适PH值
     */
    @NotNull(message = "最适PH值不能为空")
    private double optimumPh;

    private String createUsername;

    private String updateUsername;

    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public double getOptimumTemperature() {
        return optimumTemperature;
    }

    public void setOptimumTemperature(double optimumTemperature) {
        this.optimumTemperature = optimumTemperature;
    }

    public double getOptimumOxygen() {
        return optimumOxygen;
    }

    public void setOptimumOxygen(double optimumOxygen) {
        this.optimumOxygen = optimumOxygen;
    }

    public double getOptimumPh() {
        return optimumPh;
    }

    public void setOptimumPh(double optimumPh) {
        this.optimumPh = optimumPh;
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("name",name)
                .add("remark",remark)
                .add("optimumTemperature",optimumTemperature)
                .add("optimumOxygen",optimumOxygen)
                .add("optimumPh",optimumPh)
                .toString();
    }
}
