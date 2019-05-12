package com.secrething.service.impl;

import com.secrething.dao.RealDataDao;
import com.secrething.model.DataResponse;
import com.secrething.service.RealDataService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
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
    RealDataDao realDataDao;
    @Override
    public DataResponse searchPollution(String station,String[] sources) {
        try {
            SearchResponse response = realDataDao.selectPollutionData(station,sources);
            List<Map> list = new ArrayList<>();
            for (SearchHit hit:response.getHits().getHits()){
                list.add(hit.getSourceAsMap());
            }
            return DataResponse.success(list);
        }catch (Exception e){
            log.error("selectPollutionData query error",e);
            return DataResponse.error(e.getMessage());
        }
    }
}
