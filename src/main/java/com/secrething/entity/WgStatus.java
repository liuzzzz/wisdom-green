package com.secrething.entity;

import java.util.Date;

public class WgStatus {
    private String stationNo;

    private String longitude;

    private String latitude;

    private String temStation;

    private String temEnv;

    private Date publicTime;

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo == null ? null : stationNo.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getTemStation() {
        return temStation;
    }

    public void setTemStation(String temStation) {
        this.temStation = temStation == null ? null : temStation.trim();
    }

    public String getTemEnv() {
        return temEnv;
    }

    public void setTemEnv(String temEnv) {
        this.temEnv = temEnv == null ? null : temEnv.trim();
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }
}