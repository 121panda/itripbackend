package com.ytzl.itrip.biz.service.impl;
import com.ytzl.itrip.biz.service.ItripProductStoreService;
import com.ytzl.itrip.dao.mapper.ItripProductStoreMapper;
import com.ytzl.itrip.beans.model.ItripProductStore;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripProductStoreService")
public class ItripProductStoreServiceImpl implements ItripProductStoreService {

    @Resource
    private ItripProductStoreMapper itripProductStoreMapper;

    public ItripProductStore getItripProductStoreById(Long id)throws Exception{
        return itripProductStoreMapper.getItripProductStoreById(id);
    }

    public List<ItripProductStore> getItripProductStoreListByMap(Map<String,Object> param)throws Exception{
        return itripProductStoreMapper.getItripProductStoreListByMap(param);
    }

    public Integer getItripProductStoreCountByMap(Map<String,Object> param)throws Exception{
        return itripProductStoreMapper.getItripProductStoreCountByMap(param);
    }

    public Integer saveItripProductStore(ItripProductStore itripProductStore)throws Exception{
            itripProductStore.setCreationDate(new Date());
            return itripProductStoreMapper.saveItripProductStore(itripProductStore);
    }

    public Integer modifyItripProductStore(ItripProductStore itripProductStore)throws Exception{
        itripProductStore.setModifyDate(new Date());
        return itripProductStoreMapper.modifyItripProductStore(itripProductStore);
    }

    public Integer removeItripProductStoreById(Long id)throws Exception{
        return itripProductStoreMapper.removeItripProductStoreById(id);
    }

    public Page<ItripProductStore> queryItripProductStorePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripProductStoreMapper.getItripProductStoreCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripProductStore> itripProductStoreList = itripProductStoreMapper.getItripProductStoreListByMap(param);
        page.setRows(itripProductStoreList);
        return page;
    }
}
