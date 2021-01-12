// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentController.java

package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/findByCid",method = RequestMethod.POST)
    public BaseResp findByCid(@RequestBody Map map)
    {
        Integer cid = (Integer)map.get("cid");
        Integer page = (Integer)map.get("page");
        Integer size = (Integer)map.get("size");
        return commentService.findByCid(cid, page, size);
    }

    @RequestMapping("/findByScoreAndCid")
    public BaseResp findByScoreAndCid(@RequestBody Map map)
    {
        Integer cid = (Integer)map.get("cid");
        double score = Double.parseDouble(map.get("score").toString());
        Integer page = (Integer)map.get("page");
        Integer size = (Integer)map.get("size");
        return commentService.findByScoreAndCid(cid, Double.valueOf(score), page, size);
    }

    @RequestMapping("/AddComment")
    public BaseResp AddComment(@RequestBody Map map)
    {
        Integer cid = (Integer)map.get("cid");
        String des = (String)map.get("des");
        Double score = Double.valueOf(map.get("score").toString());
        return commentService.AddComment(cid, des, score);
    }

    @RequestMapping("/findByScore")
    public BaseResp findByScore(@RequestBody Map map){
        Integer cid = (Integer)map.get("cid");
        Double score = Double.valueOf(map.get("score").toString());
        Integer page = (Integer)map.get("page");
        Integer size = (Integer)map.get("size");
        return commentService.findByScoreAndCid(cid, Double.valueOf(score), page, size);
    }
}
