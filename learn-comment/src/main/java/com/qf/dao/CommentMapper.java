// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentMapper.java

package com.qf.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface CommentMapper {

    List findByCid(@Param("cid") Integer cid);

    List findByScoreAndCid(@Param("cid") Integer cid, @Param("score") Double valueOf);

    List findByScoreAndCidOne(@Param("cid") Integer cid, @Param("score") Double valueOf);

    List findByScoreAndCidTwo(@Param("cid") Integer cid, @Param("score") Double valueOf);

    Integer AddComment(@Param("cid") Integer cid, @Param("des") String des, @Param("score") Double score, @Param("date") String format, @Param("uid") Integer uid);
}
