package com.secrething.entity;

public class WgStation {
    private Integer stationId;

    private String stationNo;

    private String stationName;

    private String city;

    private String stationStatus;

    private String stationDes;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo == null ? null : stationNo.trim();
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(String stationStatus) {
        this.stationStatus = stationStatus == null ? null : stationStatus.trim();
    }

    public String getStationDes() {
        return stationDes;
    }

    public void setStationDes(String stationDes) {
        this.stationDes = stationDes == null ? null : stationDes.trim();
    }
}