package com.ytzl.itrip.biz.service;
import com.ytzl.itrip.beans.model.ItripImage;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripImageService {

    public ItripImage getItripImageById(Long id)throws Exception;

    public List<ItripImage>	getItripImageListByMap(Map<String, Object> param)throws Exception;

    public Integer getItripImageCountByMap(Map<String, Object> param)throws Exception;

    public Integer saveItripImage(ItripImage itripImage)throws Exception;

    public Integer modifyItripImage(ItripImage itripImage)throws Exception;

    public Integer removeItripImageById(Long id)throws Exception;

    public Page<ItripImage> queryItripImagePageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
