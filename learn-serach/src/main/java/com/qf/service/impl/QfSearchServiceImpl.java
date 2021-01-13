package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.controller.SerachController;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Course;
import com.qf.service.QfSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Wang on 2021/1/6 15:33
 */
@Service
public class QfSearchServiceImpl implements QfSearchService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Value("${es.index}")
    private String index;

    @Override
    public BaseResp selectKey(String key, Integer page, Integer size) {

//        声明返回值
        BaseResp baseResp = new BaseResp();

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types("doc");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(key,"title"));//title

//        分页
        int from=(page-1)*size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);


//        高亮字段
        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        要高亮的字段
        highlightBuilder.field("title");//title
//        前缀后缀
        highlightBuilder.preTags("<font style='color:red'>");
        highlightBuilder.postTags("</font>");
        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);
//       执行请求
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest);
//            解析
            SearchHits hits = search.getHits();
            long totalHits = hits.getTotalHits();
            baseResp.setTotal(totalHits);

            SearchHit[] hits1 = hits.getHits();
            List list = new ArrayList<>();
            for (SearchHit hi:hits1){
                Map<String, HighlightField> highlightFields = hi.getHighlightFields();
                String title=null;
                if (highlightFields!=null){
                    HighlightField Name = highlightFields.get("title");
                    if(Name!=null){
                        Text[] fragments = Name.getFragments();
                        StringBuffer stringBuffer = new StringBuffer();
                        if (fragments!=null){
                            for (Text te:fragments){
                                title = stringBuffer.append(te).toString();
                            }
                        }
                    }
                }
//                处理其他数据
                Map<String, Object> sourceAsMap = hi.getSourceAsMap();
                if (title!=null){
                    sourceAsMap.put("Name",title);
                }
                Object o = JSONObject.toJSON(sourceAsMap);
                Course course = JSONObject.parseObject(o.toString(), Course.class);
//                course.setId(Integer.valueOf(hi.getId()));
                list.add(course);
            }
            baseResp.setCode(200);
            baseResp.setMessage("查询成功");
            baseResp.setData(list);
            return baseResp;
        } catch (IOException e) {
            e.printStackTrace();
            baseResp.setCode(3001);
            baseResp.setMessage("查询失败");
            return baseResp;
        }
    }
}
