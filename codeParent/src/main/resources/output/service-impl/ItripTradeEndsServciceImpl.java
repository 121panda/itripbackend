package com.ytzl.itrip.service.impl;
import com.ytzl.itrip.dao.mapper.ItripTradeEndsMapper;
import com.ytzl.itrip.beans.model.ItripTradeEnds;
import com.ytzl.itrip.utils.EmptyUtils;
import com.ytzl.itrip.utils.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.Constants;
@Service("itripTradeEndsService")
public class ItripTradeEndsServiceImpl implements ItripTradeEndsService {

    @Resource
    private ItripTradeEndsMapper itripTradeEndsMapper;

    public ItripTradeEnds getItripTradeEndsById(Long id)throws Exception{
        return itripTradeEndsMapper.getItripTradeEndsById(id);
    }

    public List<ItripTradeEnds> getItripTradeEndsListByMap(Map<String,Object> param)throws Exception{
        return itripTradeEndsMapper.getItripTradeEndsListByMap(param);
    }

    public Integer getItripTradeEndsCountByMap(Map<String,Object> param)throws Exception{
        return itripTradeEndsMapper.getItripTradeEndsCountByMap(param);
    }

    public Integer saveItripTradeEnds(ItripTradeEnds itripTradeEnds)throws Exception{
            itripTradeEnds.setCreationDate(new Date());
            return itripTradeEndsMapper.saveItripTradeEnds(itripTradeEnds);
    }

    public Integer modifyItripTradeEnds(ItripTradeEnds itripTradeEnds)throws Exception{
        itripTradeEnds.setModifyDate(new Date());
        return itripTradeEndsMapper.modifyItripTradeEnds(itripTradeEnds);
    }

    public Integer removeItripTradeEndsById(Long id)throws Exception{
        return itripTradeEndsMapper.removeItripTradeEndsById(id);
    }

    public Page<ItripTradeEnds> queryItripTradeEndsPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripTradeEndsMapper.getItripTradeEndsCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripTradeEnds> itripTradeEndsList = itripTradeEndsMapper.getItripTradeEndsListByMap(param);
        page.setRows(itripTradeEndsList);
        return page;
    }
}
