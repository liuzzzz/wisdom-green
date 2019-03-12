package com.secrething.dao;

import com.secrething.entity.WgPollution;
import java.util.List;

public interface WgPollutionMapper {
    int insert(WgPollution record);

    List<WgPollution> selectAll();
}