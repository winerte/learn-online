// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentServiceImpl.java

package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.CommentMapper;
import com.qf.pojo.resp.BaseResp;
import com.qf.vo.Comment;
import com.qf.service.CommentService;
import com.qf.utils.RedisUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentMapper commentMapper;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    RedisTemplate redisTemplate;
    BaseResp baseResp = new BaseResp();
    @Override
    public BaseResp findByCid(Integer cid, Integer page, Integer size) {
        PageHelper.startPage(page.intValue(), size.intValue());
        List list = commentMapper.findByCid(cid);
        PageInfo commentPageInfo = new PageInfo(list);
        List list3 = commentMapper.findByScoreAndCid(cid, Double.valueOf(3D));
        List list1 = commentMapper.findByScoreAndCidOne(cid, Double.valueOf(2D));
        List list2 = commentMapper.findByScoreAndCidTwo(cid, Double.valueOf(0.0D));
        baseResp.setTotall(Long.valueOf(Long.parseLong(Integer.valueOf(list3.size()).toString())));
        baseResp.setTotalll(Long.valueOf(Long.parseLong(Integer.valueOf(list1.size()).toString())));
        baseResp.setTotallll(Long.valueOf(Long.parseLong(Integer.valueOf(list2.size()).toString())));
        if(commentPageInfo == null)
        {
            baseResp.setCode(Integer.valueOf(3000));
            baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u5931\u8D25");
            return baseResp;
        } else
        {
            double total = commentPageInfo.getTotal();
            int i = 0;
            Double j = Double.valueOf((double)list3.size() / total);
            Double v = Double.valueOf(j.doubleValue() * 100D);
            i = v.intValue();
            baseResp.setHapinglv((new StringBuilder()).append(i).append("%").toString());
            baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u6210\u529F");
            baseResp.setCode(Integer.valueOf(200));
            baseResp.setTotal(Long.valueOf(commentPageInfo.getTotal()));
            baseResp.setData(commentPageInfo.getList());
            return baseResp;
        }
    }

    @Override
    public BaseResp findByScoreAndCid(Integer cid, Double score, Integer page, Integer size) {
        if(score.doubleValue() == 3D)
        {
            PageHelper.startPage(page.intValue(), size.intValue());
            List list = commentMapper.findByScoreAndCid(cid, score);
            PageInfo commentPageInfo = new PageInfo(list);
            if(commentPageInfo == null)
            {
                baseResp.setCode(Integer.valueOf(3000));
                baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u5931\u8D25");
                return baseResp;
            } else
            {
                baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u6210\u529F");
                baseResp.setCode(Integer.valueOf(200));
                baseResp.setTotal(Long.valueOf(commentPageInfo.getTotal()));
                baseResp.setData(commentPageInfo.getList());
                return baseResp;
            }
        }
        if(score.doubleValue() == 2D)
        {
            PageHelper.startPage(page.intValue(), size.intValue());
            List list = commentMapper.findByScoreAndCidOne(cid, score);
            PageInfo commentPageInfo = new PageInfo(list);
            if(commentPageInfo == null)
            {
                baseResp.setCode(Integer.valueOf(3000));
                baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u5931\u8D25");
                return baseResp;
            } else
            {
                baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u6210\u529F");
                baseResp.setCode(Integer.valueOf(200));
                baseResp.setTotal(Long.valueOf(commentPageInfo.getTotal()));
                baseResp.setData(commentPageInfo.getList());
                return baseResp;
            }
        }
        if(score.doubleValue() == 0.0D)
        {
            PageHelper.startPage(page.intValue(), size.intValue());
            List list = commentMapper.findByScoreAndCidTwo(cid, score);
            PageInfo commentPageInfo = new PageInfo(list);
            if(commentPageInfo == null)
            {
                baseResp.setCode(Integer.valueOf(3000));
                baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u5931\u8D25");
                return baseResp;
            } else
            {
                baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u6210\u529F");
                baseResp.setCode(Integer.valueOf(200));
                baseResp.setTotal(Long.valueOf(commentPageInfo.getTotal()));
                baseResp.setData(commentPageInfo.getList());
                return baseResp;
            }
        } else
        {
            baseResp.setMessage("\u67E5\u8BE2\u8BC4\u8BBA\u5931\u8D25");
            baseResp.setCode(Integer.valueOf(200));
            return baseResp;
        }
    }

    Comment comment = new Comment();
    public Integer cid;
    @Override
    public BaseResp AddComment(Integer cid, String des, Double score)
    {
        comment.setCid(cid);
        comment.setDes(des);
        comment.setScore(score);
        comment.setUid(Integer.valueOf(7));
        this.cid = cid;
        redisUtils.hset((new StringBuilder()).append("comm_").append(comment.getUid()).toString(), cid.toString(), comment);
        baseResp.setCode(Integer.valueOf(200));
        baseResp.setMessage("\u8BC4\u8BBA\u6210\u529F");
        return baseResp;
    }

    //0/10 * * * * ?

    @Scheduled(cron = "0/10 * * * * ?")
    public void SaveComment()
    {
        if(comment == null || cid == null)
            return;
        boolean b = redisUtils.hHasKey((new StringBuilder()).append("comm_").append(comment.getUid()).toString(), cid.toString());
        if(b)
        {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(date);
            Integer i = commentMapper.AddComment(comment.getCid(), comment.getDes(), comment.getScore(), format, comment.getUid());
            System.out.println((new StringBuilder()).append("i = ").append(i).toString());
            redisTemplate.opsForHash().delete((new StringBuilder()).append("comm_").append(comment.getUid()).toString(), new Object[] {
                cid.toString()
            });
        } else
        {
            return;
        }
    }
}
