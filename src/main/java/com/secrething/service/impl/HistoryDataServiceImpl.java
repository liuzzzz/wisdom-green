package com.secrething.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.secrething.model.DataResponse;
import com.secrething.service.ESService;
import com.secrething.service.HistoryDataService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzz on 2019-03-13 11:30.
 */
@Slf4j
@Service
public class HistoryDataServiceImpl implements HistoryDataService {

    @Autowired
    ESService  esService;
    @Override
    public DataResponse queryFromES(String jsonParams) {
        try {
            JSONObject json = JSONObject.parseObject(jsonParams);
            String station = json.getString("station");
            Date startTime = json.getDate("startTime");
            Date endTime = json.getDate("endTime");
            List<String> indexList = json.getJSONArray("indexList").toJavaList(String.class);
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("station",station));
            queryBuilder.filter(QueryBuilders.rangeQuery("pubTime").gte(startTime).lte(endTime));
            SortBuilder sortBuilder = SortBuilders.fieldSort("pubTime").order(SortOrder.DESC);
            SearchResponse response = esService.search(queryBuilder,indexList.toArray(new String[]{}),sortBuilder);
            List<Map> list = new ArrayList<>();
            for (SearchHit hit:response.getHits().getHits()){
                list.add(hit.getSourceAsMap());
            }
            return DataResponse.success(list);
        }catch (Exception e){

            return DataResponse.error(e.getMessage());
        }
    }
}
