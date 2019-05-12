package com.secrething.dao;

import com.secrething.service.ESService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2019-05-12 15:01.
 */
@Component
public class RealDataDao {
    @Autowired
    ESService esService;
    public SearchResponse selectPollutionData(String station, String[] sources) throws Exception{
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("station.keyword",station));
        SortBuilder sortBuilder = SortBuilders.fieldSort("pubTimeLong").order(SortOrder.DESC);
        return esService.search(queryBuilder,0,1,sources,sortBuilder);

    }
}
