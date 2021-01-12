// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DetailsServiceImpl.java

package com.qf.service.impl;

import com.qf.dao.DetailsRepositry;
import com.qf.resp.BaseResp;
import com.qf.service.DetailsService;
import java.util.Optional;

public class DetailsServiceImpl
    implements DetailsService
{

    public DetailsServiceImpl()
    {
        baseResp = new BaseResp();
    }

    public BaseResp findById(Integer id)
    {
        Optional byId = detailsRepositry.findById(id);
        if(byId == null)
        {
            baseResp.setCode(Integer.valueOf(4000));
            baseResp.setMessage("\u67E5\u8BE2\u5931\u8D25");
            return baseResp;
        } else
        {
            baseResp.setCode(Integer.valueOf(200));
            baseResp.setData(byId.get());
            baseResp.setMessage("\u67E5\u8BE2\u6210\u529F");
            return baseResp;
        }
    }

    DetailsRepositry detailsRepositry;
    BaseResp baseResp;
}
