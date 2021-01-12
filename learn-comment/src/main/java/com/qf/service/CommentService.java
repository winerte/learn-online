// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentService.java

package com.qf.service;

import com.qf.resp.BaseResp;

public interface CommentService {
    BaseResp findByCid(Integer cid, Integer page, Integer size);

    BaseResp findByScoreAndCid(Integer cid, Double valueOf, Integer page, Integer size);

    BaseResp AddComment(Integer cid, String des, Double score);
}
