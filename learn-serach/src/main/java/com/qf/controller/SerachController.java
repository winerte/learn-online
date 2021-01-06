package com.qf.controller;

import com.qf.resp.BaseResp;
import com.qf.service.QfSearchService;
import org.elasticsearch.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Wang on 2021/1/6 15:18
 */
@RestController
@RequestMapping("/serach")
public class SerachController {

    @Autowired
    QfSearchService qfsearchService;

    @RequestMapping("/selectKey/{key}/{page}/{size}")
    public BaseResp selectKey(@PathVariable("key")String key,@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        return qfsearchService.selectKey(key,page,size);
    }
}
