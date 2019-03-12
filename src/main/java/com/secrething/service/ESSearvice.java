package com.secrething.service;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

/**
 * Created by xiaoq on 2019-03-06 17:34.
 */
public interface ESSearvice {

    SearchResponse search(QueryBuilder builder,String[] sources) throws Exception;

    BulkResponse insert(List list) throws Exception;
}
