package com.ytzl.itrip.search.dao;

import com.ytzl.itrip.utils.common.Constants;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class BaseQuery<T> {
    private HttpSolrClient solrClient;

    public BaseQuery(String baseUrl) {
        solrClient = new HttpSolrClient(baseUrl);
        // 设置解析器
        solrClient.setParser(new XMLResponseParser());
        // 设置延迟时间
        solrClient.setConnectionTimeout(1000);

    }

    /**
     * 分页查询
     * @param solrQuery solr查询对象
     * @param pageNo 当前页数
     * @param pageSize 每页多少条
     * @param clazz 泛型类型
     * @return
     */
    public Page<T> queryPage(SolrQuery solrQuery, Integer pageNo,
                             Integer pageSize, Class clazz) throws IOException, SolrServerException {
        Integer currPage = EmptyUtils.isNotEmpty(pageNo) ? pageNo :
                Constants.DEFAULT_PAGE_NO;
        Integer startSize = EmptyUtils.isNotEmpty(pageSize) ?
                pageSize : Constants.DEFAULT_PAGE_SIZE;
        // 返回查询结果
        QueryResponse queryResponse = solrClient.query(solrQuery);
        // 获取总条数
        Integer total = Long.valueOf(queryResponse.getResults().getNumFound()).intValue();
        // 获取数据
        Page<T> page = new Page<>(currPage,startSize,total);
        // 获取数据
        List<T> rows = queryResponse.getBeans(clazz);
        page.setRows(rows);
        return page;
    }
    public List<T> querList(SolrQuery solrQuery, Integer pageSize,Class clazz) throws IOException, SolrServerException {
        if(EmptyUtils.isEmpty(pageSize)) {
        solrQuery.setRows(0);
        solrQuery.setRows(pageSize);
        }
        QueryResponse queryResponse = solrClient.query(solrQuery);
        // 返回查询结果
        return queryResponse.getBeans(clazz);
    }
}
