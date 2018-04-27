package com.ytzl.itrip.biz.service.impl;
import com.ytzl.itrip.biz.service.ItripHotelTradingAreaService;
import com.ytzl.itrip.dao.mapper.ItripHotelTradingAreaMapper;
import com.ytzl.itrip.beans.model.ItripHotelTradingArea;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripHotelTradingAreaService")
public class ItripHotelTradingAreaServiceImpl implements ItripHotelTradingAreaService {

    @Resource
    private ItripHotelTradingAreaMapper itripHotelTradingAreaMapper;

    public ItripHotelTradingArea getItripHotelTradingAreaById(Long id)throws Exception{
        return itripHotelTradingAreaMapper.getItripHotelTradingAreaById(id);
    }

    public List<ItripHotelTradingArea> getItripHotelTradingAreaListByMap(Map<String,Object> param)throws Exception{
        return itripHotelTradingAreaMapper.getItripHotelTradingAreaListByMap(param);
    }

    public Integer getItripHotelTradingAreaCountByMap(Map<String,Object> param)throws Exception{
        return itripHotelTradingAreaMapper.getItripHotelTradingAreaCountByMap(param);
    }

    public Integer saveItripHotelTradingArea(ItripHotelTradingArea itripHotelTradingArea)throws Exception{
            itripHotelTradingArea.setCreationDate(new Date());
            return itripHotelTradingAreaMapper.saveItripHotelTradingArea(itripHotelTradingArea);
    }

    public Integer modifyItripHotelTradingArea(ItripHotelTradingArea itripHotelTradingArea)throws Exception{
        itripHotelTradingArea.setModifyDate(new Date());
        return itripHotelTradingAreaMapper.modifyItripHotelTradingArea(itripHotelTradingArea);
    }

    public Integer removeItripHotelTradingAreaById(Long id)throws Exception{
        return itripHotelTradingAreaMapper.removeItripHotelTradingAreaById(id);
    }

    public Page<ItripHotelTradingArea> queryItripHotelTradingAreaPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripHotelTradingAreaMapper.getItripHotelTradingAreaCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelTradingArea> itripHotelTradingAreaList = itripHotelTradingAreaMapper.getItripHotelTradingAreaListByMap(param);
        page.setRows(itripHotelTradingAreaList);
        return page;
    }
}
