package com.ztqees.vo;

import java.io.Serializable;

/**
 * @author zhouqien
 * @date 2021-10-21 14:36
 */
public class UnitMessage implements Serializable {
    private String buildingCode;
    private Integer unitCount;

    public UnitMessage() {
    }

    public UnitMessage(String buildingCode, Integer unitCount) {
        this.buildingCode = buildingCode;
        this.unitCount = unitCount;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Integer getunitCount() {
        return unitCount;
    }

    @Override
    public String toString() {
        return "UnitMessage{" +
                "buildingCode='" + buildingCode + '\'' +
                ", unitCount='" + unitCount + '\'' +
                '}';
    }

    public void setunitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }
}
