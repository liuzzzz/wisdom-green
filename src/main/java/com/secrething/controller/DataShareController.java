package com.secrething.controller;

import com.alibaba.fastjson.JSONObject;
import com.secrething.model.DataResponse;
import com.secrething.service.HistoryDataService;
import com.secrething.service.RealDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuzz on 2019-03-13 11:21.
 */
@RestController
@RequestMapping("dataService")
public class DataShareController {


    @Autowired
    RealDataService realDataService;

    @Autowired
    HistoryDataService historyDataService;


    @RequestMapping("realTimeData")
    public DataResponse realDataAPI(@RequestBody String jsonParams) {
        JSONObject json = JSONObject.parseObject(jsonParams);
        String station = json.getString("station");
        List<String> indexList = json.getJSONArray("indexList").toJavaList(String.class);
        return realDataService.searchPollution(station,indexList.toArray(new String[]{}));
    }

    @RequestMapping("queryFromES")
    public DataResponse historyDataAPI(@RequestBody String jsonParams) {
        return historyDataService.queryFromES(jsonParams);
    }
}
