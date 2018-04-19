package com.ytzl.itrip.service;
import com.ytzl.itrip.beans.model.ItripHotelTempStore;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripHotelTempStoreService {

    public ItripHotelTempStore getItripHotelTempStoreById(Long id)throws Exception;

    public List<ItripHotelTempStore>	getItripHotelTempStoreListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelTempStoreCountByMap(Map<String,Object> param)throws Exception;

    public Integer saveItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

    public Integer modifyItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

    public Integer removeItripHotelTempStoreById(Long id)throws Exception;

    public Page<ItripHotelTempStore> queryItripHotelTempStorePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
