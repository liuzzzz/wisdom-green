package com.secrething.dao;

import com.secrething.entity.WgUser;
import java.util.List;

public interface WgUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(WgUser record);

    WgUser selectByPrimaryKey(Integer userId);

    List<WgUser> selectAll();

    int updateByPrimaryKey(WgUser record);
}