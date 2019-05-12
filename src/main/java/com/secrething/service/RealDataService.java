package com.secrething.service;

import com.secrething.model.DataResponse;

/**
 * Created by liuzz on 2019-03-13 11:22.
 */
public interface RealDataService {
    DataResponse searchPollution(String station,String[] sources);
}
