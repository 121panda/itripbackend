package com.ytzl.itrip.auth.service.impl;
import com.ytzl.itrip.dao.mapper.ItripHotelMapper;
import com.ytzl.itrip.beans.model.ItripHotel;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripHotelService")
public class ItripHotelServiceImpl implements ItripHotelService {

    @Resource
    private ItripHotelMapper itripHotelMapper;

    public ItripHotel getItripHotelById(Long id)throws Exception{
        return itripHotelMapper.getItripHotelById(id);
    }

    public List<ItripHotel> getItripHotelListByMap(Map<String,Object> param)throws Exception{
        return itripHotelMapper.getItripHotelListByMap(param);
    }

    public Integer getItripHotelCountByMap(Map<String,Object> param)throws Exception{
        return itripHotelMapper.getItripHotelCountByMap(param);
    }

    public Integer saveItripHotel(ItripHotel itripHotel)throws Exception{
            itripHotel.setCreationDate(new Date());
            return itripHotelMapper.saveItripHotel(itripHotel);
    }

    public Integer modifyItripHotel(ItripHotel itripHotel)throws Exception{
        itripHotel.setModifyDate(new Date());
        return itripHotelMapper.modifyItripHotel(itripHotel);
    }

    public Integer removeItripHotelById(Long id)throws Exception{
        return itripHotelMapper.removeItripHotelById(id);
    }

    public Page<ItripHotel> queryItripHotelPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripHotelMapper.getItripHotelCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotel> itripHotelList = itripHotelMapper.getItripHotelListByMap(param);
        page.setRows(itripHotelList);
        return page;
    }
}
