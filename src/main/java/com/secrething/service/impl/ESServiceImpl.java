package com.secrething.service.impl;

import com.secrething.service.ESSearvice;
import com.secrething.utils.ESUtil;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

/**
 * Created by xiaoq on 2019-03-06 17:36.
 */
public class ESServiceImpl implements ESSearvice {
    @Override
    public SearchResponse search(QueryBuilder builder, String[] sources) throws Exception{
        return ESUtil.search(builder, sources);
    }

    @Override
    public BulkResponse insert(List list) throws Exception{
        return ESUtil.insert(list);
    }
}
