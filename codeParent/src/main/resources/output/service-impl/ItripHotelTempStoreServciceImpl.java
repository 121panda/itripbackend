package com.ytzl.itrip.service.impl;
import com.ytzl.itrip.dao.mapper.ItripHotelTempStoreMapper;
import com.ytzl.itrip.beans.model.ItripHotelTempStore;
import com.ytzl.itrip.utils.EmptyUtils;
import com.ytzl.itrip.utils.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.Constants;
@Service("itripHotelTempStoreService")
public class ItripHotelTempStoreServiceImpl implements ItripHotelTempStoreService {

    @Resource
    private ItripHotelTempStoreMapper itripHotelTempStoreMapper;

    public ItripHotelTempStore getItripHotelTempStoreById(Long id)throws Exception{
        return itripHotelTempStoreMapper.getItripHotelTempStoreById(id);
    }

    public List<ItripHotelTempStore> getItripHotelTempStoreListByMap(Map<String,Object> param)throws Exception{
        return itripHotelTempStoreMapper.getItripHotelTempStoreListByMap(param);
    }

    public Integer getItripHotelTempStoreCountByMap(Map<String,Object> param)throws Exception{
        return itripHotelTempStoreMapper.getItripHotelTempStoreCountByMap(param);
    }

    public Integer saveItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception{
            itripHotelTempStore.setCreationDate(new Date());
            return itripHotelTempStoreMapper.saveItripHotelTempStore(itripHotelTempStore);
    }

    public Integer modifyItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception{
        itripHotelTempStore.setModifyDate(new Date());
        return itripHotelTempStoreMapper.modifyItripHotelTempStore(itripHotelTempStore);
    }

    public Integer removeItripHotelTempStoreById(Long id)throws Exception{
        return itripHotelTempStoreMapper.removeItripHotelTempStoreById(id);
    }

    public Page<ItripHotelTempStore> queryItripHotelTempStorePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripHotelTempStoreMapper.getItripHotelTempStoreCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelTempStore> itripHotelTempStoreList = itripHotelTempStoreMapper.getItripHotelTempStoreListByMap(param);
        page.setRows(itripHotelTempStoreList);
        return page;
    }
}
