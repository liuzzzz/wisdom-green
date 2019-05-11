package com.secrething.service.impl;

import com.secrething.service.ESService;
import com.secrething.utils.ESQueryUtil;
import com.secrething.utils.ESUtil;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaoq on 2019-03-06 17:36.
 */
@Service
public class ESServiceImpl implements ESService {
    @Override
    public SearchResponse search(QueryBuilder builder, String[] sources) throws Exception {
        return ESUtil.search(builder, sources);
    }

    @Override
    public SearchResponse search(QueryBuilder builder, String[] sources, SortBuilder sortBuilder) throws Exception {
        return ESQueryUtil.multiCondiQuery(builder, sources, sortBuilder);
    }
    @Override
    public SearchResponse search(QueryBuilder builder, int from, int size, String[] sources) throws Exception {
        return ESUtil.search(builder, sources,from,size,null);
    }

    @Override
    public SearchResponse search(QueryBuilder builder, int from, int size, String[] sources, SortBuilder sortBuilder) throws Exception {
        return ESUtil.search(builder, sources,from,size,sortBuilder);
    }

    @Override
    public BulkResponse insert(List list) throws Exception {
        return ESUtil.insert(list);
    }
}
