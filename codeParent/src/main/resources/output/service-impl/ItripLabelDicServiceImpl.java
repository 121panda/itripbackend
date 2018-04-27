package com.ytzl.itrip.auth.service.impl;
import com.ytzl.itrip.dao.mapper.ItripLabelDicMapper;
import com.ytzl.itrip.beans.model.ItripLabelDic;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripLabelDicService")
public class ItripLabelDicServiceImpl implements ItripLabelDicService {

    @Resource
    private ItripLabelDicMapper itripLabelDicMapper;

    public ItripLabelDic getItripLabelDicById(Long id)throws Exception{
        return itripLabelDicMapper.getItripLabelDicById(id);
    }

    public List<ItripLabelDic> getItripLabelDicListByMap(Map<String,Object> param)throws Exception{
        return itripLabelDicMapper.getItripLabelDicListByMap(param);
    }

    public Integer getItripLabelDicCountByMap(Map<String,Object> param)throws Exception{
        return itripLabelDicMapper.getItripLabelDicCountByMap(param);
    }

    public Integer saveItripLabelDic(ItripLabelDic itripLabelDic)throws Exception{
            itripLabelDic.setCreationDate(new Date());
            return itripLabelDicMapper.saveItripLabelDic(itripLabelDic);
    }

    public Integer modifyItripLabelDic(ItripLabelDic itripLabelDic)throws Exception{
        itripLabelDic.setModifyDate(new Date());
        return itripLabelDicMapper.modifyItripLabelDic(itripLabelDic);
    }

    public Integer removeItripLabelDicById(Long id)throws Exception{
        return itripLabelDicMapper.removeItripLabelDicById(id);
    }

    public Page<ItripLabelDic> queryItripLabelDicPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripLabelDicMapper.getItripLabelDicCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripLabelDic> itripLabelDicList = itripLabelDicMapper.getItripLabelDicListByMap(param);
        page.setRows(itripLabelDicList);
        return page;
    }
}
