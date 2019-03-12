package com.secrething.entity;

import java.util.Date;

public class WgPollution {
    private String stationNum;

    private String pm25;

    private String pm10;

    private String so2;

    private String no2;

    private String o3;

    private String co;

    private String realAqi;

    private String prdAqi;

    private Date publicTime;

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum == null ? null : stationNum.trim();
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25 == null ? null : pm25.trim();
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10 == null ? null : pm10.trim();
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2 == null ? null : so2.trim();
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2 == null ? null : no2.trim();
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3 == null ? null : o3.trim();
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co == null ? null : co.trim();
    }

    public String getRealAqi() {
        return realAqi;
    }

    public void setRealAqi(String realAqi) {
        this.realAqi = realAqi == null ? null : realAqi.trim();
    }

    public String getPrdAqi() {
        return prdAqi;
    }

    public void setPrdAqi(String prdAqi) {
        this.prdAqi = prdAqi == null ? null : prdAqi.trim();
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }
}