package com.secrething.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.secrething.model.DataResponse;
import com.secrething.service.ESService;
import com.secrething.service.RealDataService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzz on 2019-03-13 11:29.
 */
@Slf4j
@Service
public class RealDataServiceImpl implements RealDataService {

    @Autowired
    ESService esService;
    @Override
    public DataResponse realData(String jsonParams) {
        try {
            JSONObject json = JSONObject.parseObject(jsonParams);
            String station = json.getString("station");
            List<String> indexList = json.getJSONArray("indexList").toJavaList(String.class);
            QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("station",station));
            SortBuilder sortBuilder = SortBuilders.fieldSort("pubTime").order(SortOrder.DESC);
            SearchResponse response = esService.search(queryBuilder,indexList.toArray(new String[]{}),0,1,sortBuilder);
            List<Map> list = new ArrayList<>();
            for (SearchHit hit:response.getHits().getHits()){
                list.add(hit.getSourceAsMap());
            }
            return DataResponse.success(list);
        }catch (Exception e){
            log.error("realData query error",e);
            return DataResponse.error(e.getMessage());
        }
    }
}
