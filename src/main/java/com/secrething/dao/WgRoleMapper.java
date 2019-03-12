package com.secrething.dao;

import com.secrething.entity.WgRole;
import java.util.List;

public interface WgRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(WgRole record);

    WgRole selectByPrimaryKey(Integer roleId);

    List<WgRole> selectAll();

    int updateByPrimaryKey(WgRole record);
}