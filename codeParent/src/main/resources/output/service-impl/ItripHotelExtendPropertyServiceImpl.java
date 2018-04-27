package com.ytzl.itrip.auth.service.impl;
import com.ytzl.itrip.dao.mapper.ItripHotelExtendPropertyMapper;
import com.ytzl.itrip.beans.model.ItripHotelExtendProperty;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripHotelExtendPropertyService")
public class ItripHotelExtendPropertyServiceImpl implements ItripHotelExtendPropertyService {

    @Resource
    private ItripHotelExtendPropertyMapper itripHotelExtendPropertyMapper;

    public ItripHotelExtendProperty getItripHotelExtendPropertyById(Long id)throws Exception{
        return itripHotelExtendPropertyMapper.getItripHotelExtendPropertyById(id);
    }

    public List<ItripHotelExtendProperty> getItripHotelExtendPropertyListByMap(Map<String,Object> param)throws Exception{
        return itripHotelExtendPropertyMapper.getItripHotelExtendPropertyListByMap(param);
    }

    public Integer getItripHotelExtendPropertyCountByMap(Map<String,Object> param)throws Exception{
        return itripHotelExtendPropertyMapper.getItripHotelExtendPropertyCountByMap(param);
    }

    public Integer saveItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception{
            itripHotelExtendProperty.setCreationDate(new Date());
            return itripHotelExtendPropertyMapper.saveItripHotelExtendProperty(itripHotelExtendProperty);
    }

    public Integer modifyItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception{
        itripHotelExtendProperty.setModifyDate(new Date());
        return itripHotelExtendPropertyMapper.modifyItripHotelExtendProperty(itripHotelExtendProperty);
    }

    public Integer removeItripHotelExtendPropertyById(Long id)throws Exception{
        return itripHotelExtendPropertyMapper.removeItripHotelExtendPropertyById(id);
    }

    public Page<ItripHotelExtendProperty> queryItripHotelExtendPropertyPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripHotelExtendPropertyMapper.getItripHotelExtendPropertyCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelExtendProperty> itripHotelExtendPropertyList = itripHotelExtendPropertyMapper.getItripHotelExtendPropertyListByMap(param);
        page.setRows(itripHotelExtendPropertyList);
        return page;
    }
}
