package com.ytzl.itrip.auth.service;
import com.ytzl.itrip.beans.model.ItripTradeEnds;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripTradeEndsService {

    public ItripTradeEnds getItripTradeEndsById(Long id)throws Exception;

    public List<ItripTradeEnds>	getItripTradeEndsListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripTradeEndsCountByMap(Map<String,Object> param)throws Exception;

    public Integer saveItripTradeEnds(ItripTradeEnds itripTradeEnds)throws Exception;

    public Integer modifyItripTradeEnds(ItripTradeEnds itripTradeEnds)throws Exception;

    public Integer removeItripTradeEndsById(Long id)throws Exception;

    public Page<ItripTradeEnds> queryItripTradeEndsPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
