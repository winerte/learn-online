package com.qf.service;

import com.qf.resp.BaseResp;

/**
 * Created by Wang on 2021/1/6 15:33
 */
public interface QfSearchService {

    BaseResp selectKey(String key, Integer page, Integer size);
}
