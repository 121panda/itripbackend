package com.ytzl.itrip.service;
import com.ytzl.itrip.beans.model.ItripLabelDic;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripLabelDicService {

    public ItripLabelDic getItripLabelDicById(Long id)throws Exception;

    public List<ItripLabelDic>	getItripLabelDicListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripLabelDicCountByMap(Map<String,Object> param)throws Exception;

    public Integer saveItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

    public Integer modifyItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

    public Integer removeItripLabelDicById(Long id)throws Exception;

    public Page<ItripLabelDic> queryItripLabelDicPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
