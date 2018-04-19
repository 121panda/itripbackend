package com.ytzl.itrip.service;
import com.ytzl.itrip.beans.model.ItripHotelTradingArea;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripHotelTradingAreaService {

    public ItripHotelTradingArea getItripHotelTradingAreaById(Long id)throws Exception;

    public List<ItripHotelTradingArea>	getItripHotelTradingAreaListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelTradingAreaCountByMap(Map<String,Object> param)throws Exception;

    public Integer saveItripHotelTradingArea(ItripHotelTradingArea itripHotelTradingArea)throws Exception;

    public Integer modifyItripHotelTradingArea(ItripHotelTradingArea itripHotelTradingArea)throws Exception;

    public Integer removeItripHotelTradingAreaById(Long id)throws Exception;

    public Page<ItripHotelTradingArea> queryItripHotelTradingAreaPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
