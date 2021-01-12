// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DetailsController.java

package com.qf.controller;

import com.qf.resp.BaseResp;
import com.qf.service.DetailsService;
import java.util.Map;

public class DetailsController
{

    public DetailsController()
    {
    }

    public BaseResp findById(Map map)
    {
        Integer id = (Integer)map.get("id");
        return detailsService.findById(id);
    }

    DetailsService detailsService;
}
