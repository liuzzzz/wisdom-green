package com.secrething.dao;

import com.secrething.entity.WgStation;
import java.util.List;

public interface WgStationMapper {
    int deleteByPrimaryKey(Integer stationId);

    int insert(WgStation record);

    WgStation selectByPrimaryKey(Integer stationId);

    List<WgStation> selectAll();

    int updateByPrimaryKey(WgStation record);
}