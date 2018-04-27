package com.ytzl.itrip.biz.service;
import com.ytzl.itrip.beans.model.ItripHotelFeature;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripHotelFeatureService {

    public ItripHotelFeature getItripHotelFeatureById(Long id)throws Exception;

    public List<ItripHotelFeature>	getItripHotelFeatureListByMap(Map<String, Object> param)throws Exception;

    public Integer getItripHotelFeatureCountByMap(Map<String, Object> param)throws Exception;

    public Integer saveItripHotelFeature(ItripHotelFeature itripHotelFeature)throws Exception;

    public Integer modifyItripHotelFeature(ItripHotelFeature itripHotelFeature)throws Exception;

    public Integer removeItripHotelFeatureById(Long id)throws Exception;

    public Page<ItripHotelFeature> queryItripHotelFeaturePageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
