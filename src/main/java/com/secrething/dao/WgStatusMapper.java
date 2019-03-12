package com.secrething.dao;

import com.secrething.entity.WgStatus;
import java.util.List;

public interface WgStatusMapper {
    int insert(WgStatus record);

    List<WgStatus> selectAll();
}