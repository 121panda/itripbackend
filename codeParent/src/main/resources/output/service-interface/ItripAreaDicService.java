package com.ytzl.itrip.service;
import com.ytzl.itrip.beans.model.ItripAreaDic;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripAreaDicService {

    public ItripAreaDic getItripAreaDicById(Long id)throws Exception;

    public List<ItripAreaDic>	getItripAreaDicListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripAreaDicCountByMap(Map<String,Object> param)throws Exception;

    public Integer saveItripAreaDic(ItripAreaDic itripAreaDic)throws Exception;

    public Integer modifyItripAreaDic(ItripAreaDic itripAreaDic)throws Exception;

    public Integer removeItripAreaDicById(Long id)throws Exception;

    public Page<ItripAreaDic> queryItripAreaDicPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
