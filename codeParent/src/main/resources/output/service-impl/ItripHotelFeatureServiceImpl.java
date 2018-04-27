package com.ytzl.itrip.auth.service.impl;
import com.ytzl.itrip.dao.mapper.ItripHotelFeatureMapper;
import com.ytzl.itrip.beans.model.ItripHotelFeature;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripHotelFeatureService")
public class ItripHotelFeatureServiceImpl implements ItripHotelFeatureService {

    @Resource
    private ItripHotelFeatureMapper itripHotelFeatureMapper;

    public ItripHotelFeature getItripHotelFeatureById(Long id)throws Exception{
        return itripHotelFeatureMapper.getItripHotelFeatureById(id);
    }

    public List<ItripHotelFeature> getItripHotelFeatureListByMap(Map<String,Object> param)throws Exception{
        return itripHotelFeatureMapper.getItripHotelFeatureListByMap(param);
    }

    public Integer getItripHotelFeatureCountByMap(Map<String,Object> param)throws Exception{
        return itripHotelFeatureMapper.getItripHotelFeatureCountByMap(param);
    }

    public Integer saveItripHotelFeature(ItripHotelFeature itripHotelFeature)throws Exception{
            itripHotelFeature.setCreationDate(new Date());
            return itripHotelFeatureMapper.saveItripHotelFeature(itripHotelFeature);
    }

    public Integer modifyItripHotelFeature(ItripHotelFeature itripHotelFeature)throws Exception{
        itripHotelFeature.setModifyDate(new Date());
        return itripHotelFeatureMapper.modifyItripHotelFeature(itripHotelFeature);
    }

    public Integer removeItripHotelFeatureById(Long id)throws Exception{
        return itripHotelFeatureMapper.removeItripHotelFeatureById(id);
    }

    public Page<ItripHotelFeature> queryItripHotelFeaturePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripHotelFeatureMapper.getItripHotelFeatureCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelFeature> itripHotelFeatureList = itripHotelFeatureMapper.getItripHotelFeatureListByMap(param);
        page.setRows(itripHotelFeatureList);
        return page;
    }
}
