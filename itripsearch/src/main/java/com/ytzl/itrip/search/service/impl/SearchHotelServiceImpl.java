package com.ytzl.itrip.search.service.impl;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.ytzl.itrip.beans.vo.SearchHotCityVO;
import com.ytzl.itrip.beans.vo.SearchHotelVO;
import com.ytzl.itrip.search.beans.Hotel;
import com.ytzl.itrip.search.dao.BaseQuery;
import com.ytzl.itrip.search.service.SeachHotelService;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import com.ytzl.itrip.utils.common.PropertiesUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */
@Service("seachHotelService")
public class SearchHotelServiceImpl implements SeachHotelService {
    private String baseUrl = PropertiesUtils.get("public.properties", "baseUrl");
//    @Resource
    private BaseQuery<Hotel> hotelSolrQuery = new BaseQuery<>(baseUrl);
    @Override
    public Page<Hotel> searchHotelPage(SearchHotelVO searchHotelVO) throws Exception {
        SolrQuery solrQuery = new SolrQuery("*:*");
        StringBuffer tempQuery = new StringBuffer();
        // 拼接全文检索  q查询   目的地和关键字
        if(EmptyUtils.isNotEmpty(searchHotelVO.getDestination())) {
            tempQuery.append("destination:"+searchHotelVO.getDestination());
        }
        if(EmptyUtils.isNotEmpty(searchHotelVO.getKeywords())) {
            tempQuery.append(" AND keyword:" + searchHotelVO.getKeywords());
        }
        solrQuery.setQuery(tempQuery.toString());
        // 酒店级别
        if(EmptyUtils.isNotEmpty(searchHotelVO.getHotelLevel())) {
            solrQuery.addFilterQuery(" hotelLevel:" + searchHotelVO.getHotelLevel());
        }

        // 酒店特色id
        if(EmptyUtils.isNotEmpty(searchHotelVO.getFeatureIds())) {
            String[] split = searchHotelVO.getFeatureIds().split(",");
            StringBuffer fqQuery = new StringBuffer();
            for(int i =0; i<split.length; i++){
                String tureId = split[i];
                if(i == 0){
                    // 第一次循环
                    fqQuery.append(" featureIds:*," + tureId + ",*");
                } else {
                    fqQuery.append(" OR featureIds:*," + tureId + ",*");
                }
            }
            solrQuery.addFilterQuery(fqQuery.toString());
        }
        // 最高价
        if(EmptyUtils.isNotEmpty(searchHotelVO.getMaxPrice())) {
            solrQuery.addFilterQuery(" maxPrice:[* TO " + searchHotelVO.getMaxPrice() +"]");
        }
        // 最低价
        if(EmptyUtils.isNotEmpty(searchHotelVO.getMinPrice())) {
            solrQuery.addFilterQuery(" minPrice:[" + searchHotelVO.getMaxPrice() + " TO *]");
        }
        // 商圈id
        if(EmptyUtils.isNotEmpty(searchHotelVO.getTradeAreaIds())) {
            String[] split = searchHotelVO.getTradeAreaIds().split(",");
            StringBuffer fqQuery = new StringBuffer();
            for(int i =0; i<split.length; i++){
                String areaId = split[i];
                if(i == 0){
                    // 第一次循环
                    fqQuery.append(" tradingAreaIds:*," + areaId + ",*");
                } else {
                    fqQuery.append(" OR tradingAreaIds:*," + areaId + ",*");
                }
            }
            solrQuery.addFilterQuery(fqQuery.toString());
        }
        // 字段降序
        if(EmptyUtils.isNotEmpty(searchHotelVO.getAscSort())) {
            solrQuery.addSort(searchHotelVO.getDescSort(), SolrQuery.ORDER.desc);
        }
        // 字段升序
        if(EmptyUtils.isNotEmpty(searchHotelVO.getAscSort())) {
            solrQuery.addSort(searchHotelVO.getAscSort(), SolrQuery.ORDER.asc);
        }
        return hotelSolrQuery.queryPage(solrQuery,searchHotelVO.getPageNo(),
                searchHotelVO.getPageSize(),Hotel.class);
    }

    @Override
    public List<Hotel> searchHotelByHotCity(SearchHotCityVO searchHotCityVO) throws Exception {
        // 创建solr查询， 指定q参数为查询全部
        SolrQuery solrQuery = new SolrQuery("*:*");
        // 添加过滤查询
        if(EmptyUtils.isNotEmpty(searchHotCityVO.getCityId())) {
            solrQuery.addFilterQuery("cityId:"+ searchHotCityVO.getCityId());
        }
        return hotelSolrQuery.querList(solrQuery, searchHotCityVO.getCount(), Hotel.class);
    }
}
