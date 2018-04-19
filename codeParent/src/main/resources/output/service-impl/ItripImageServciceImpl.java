package com.ytzl.itrip.service.impl;
import com.ytzl.itrip.dao.mapper.ItripImageMapper;
import com.ytzl.itrip.beans.model.ItripImage;
import com.ytzl.itrip.utils.EmptyUtils;
import com.ytzl.itrip.utils.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.Constants;
@Service("itripImageService")
public class ItripImageServiceImpl implements ItripImageService {

    @Resource
    private ItripImageMapper itripImageMapper;

    public ItripImage getItripImageById(Long id)throws Exception{
        return itripImageMapper.getItripImageById(id);
    }

    public List<ItripImage> getItripImageListByMap(Map<String,Object> param)throws Exception{
        return itripImageMapper.getItripImageListByMap(param);
    }

    public Integer getItripImageCountByMap(Map<String,Object> param)throws Exception{
        return itripImageMapper.getItripImageCountByMap(param);
    }

    public Integer saveItripImage(ItripImage itripImage)throws Exception{
            itripImage.setCreationDate(new Date());
            return itripImageMapper.saveItripImage(itripImage);
    }

    public Integer modifyItripImage(ItripImage itripImage)throws Exception{
        itripImage.setModifyDate(new Date());
        return itripImageMapper.modifyItripImage(itripImage);
    }

    public Integer removeItripImageById(Long id)throws Exception{
        return itripImageMapper.removeItripImageById(id);
    }

    public Page<ItripImage> queryItripImagePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripImageMapper.getItripImageCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripImage> itripImageList = itripImageMapper.getItripImageListByMap(param);
        page.setRows(itripImageList);
        return page;
    }
}
