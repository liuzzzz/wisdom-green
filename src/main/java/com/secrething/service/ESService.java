package com.secrething.service;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.util.List;

/**
 * Created by xiaoq on 2019-03-06 17:34.
 */
public interface ESService {

    SearchResponse search(QueryBuilder builder, String[] sources) throws Exception;

    SearchResponse search(QueryBuilder builder, String[] sources, SortBuilder sortBuilder) throws Exception;

    SearchResponse search(QueryBuilder builder, String[] sources, int from, int size) throws Exception;
    SearchResponse search(QueryBuilder builder, String[] sources, int from, int size, SortBuilder sortBuilder) throws Exception;

    BulkResponse insert(List list) throws Exception;
}
