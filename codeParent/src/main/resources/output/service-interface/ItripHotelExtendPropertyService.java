package com.ytzl.itrip.auth.service;
import com.ytzl.itrip.beans.model.ItripHotelExtendProperty;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripHotelExtendPropertyService {

    public ItripHotelExtendProperty getItripHotelExtendPropertyById(Long id)throws Exception;

    public List<ItripHotelExtendProperty>	getItripHotelExtendPropertyListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelExtendPropertyCountByMap(Map<String,Object> param)throws Exception;

    public Integer saveItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception;

    public Integer modifyItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception;

    public Integer removeItripHotelExtendPropertyById(Long id)throws Exception;

    public Page<ItripHotelExtendProperty> queryItripHotelExtendPropertyPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
