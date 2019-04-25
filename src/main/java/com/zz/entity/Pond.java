package com.zz.entity;

import com.google.common.base.MoreObjects;
import com.zz.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/7
 * Time: 23:29
 * Description: No Description
 */
public class Pond extends BaseEntity {

    @NotNull(message = "池塘代号不能为空")
    @Length(min = 1, max = 64, message = "池塘代号长度必须在1~64字符之间")
    private String name;

    @NotNull(message = "池塘备注不能为空")
    @Length(min = 1, max = 500, message = "池塘备注长度必须在1~500字符之间")
    private String remark;

    @NotNull(message = "养鱼种类不能为空")
    private Long breedFishId;

    private String fishName;

    /**
     * 测温串口号
     */
    private String comTemperature;
    /**
     * 测含氧量串口号
     */
    private String comOxygen;
    /**
     * 测ph值串口号
     */
    private String comPh;

    private String createUsername;

    private String updateUsername;

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
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

    public Long getBreedFishId() {
        return breedFishId;
    }

    public void setBreedFishId(Long breedFishId) {
        this.breedFishId = breedFishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("name",name)
                .add("remark",remark)
                .toString();
    }
}
