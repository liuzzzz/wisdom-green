package com.secrething.controller;

import com.secrething.model.DataResponse;
import com.secrething.service.HistoryDataService;
import com.secrething.service.RealDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping("realData")
    public DataResponse realDataAPI(String jsonParams) {
        return realDataService.realData(jsonParams);
    }

    @RequestMapping("queryFromES")
    public DataResponse historyDataAPI(String jsonParams) {
        return historyDataService.queryFromES(jsonParams);
    }
}
