package com.secrething.utils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;

/**
 * Created by liuzz on 2019-05-11 17:50.
 */
public class ESQueryUtil {

    public static SearchResponse multiCondiQuery(QueryBuilder builder, String[] sources, SortBuilder sortBuilder) throws Exception {
        return ESUtil.search(builder, sources, sortBuilder);
    }

}
