package com.ytzl.itrip.biz.service.impl;
import com.ytzl.itrip.biz.service.ItripCommentService;
import com.ytzl.itrip.dao.mapper.ItripCommentMapper;
import com.ytzl.itrip.beans.model.ItripComment;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripCommentService")
public class ItripCommentServiceImpl implements ItripCommentService {

    @Resource
    private ItripCommentMapper itripCommentMapper;

    public ItripComment getItripCommentById(Long id)throws Exception{
        return itripCommentMapper.getItripCommentById(id);
    }

    public List<ItripComment> getItripCommentListByMap(Map<String,Object> param)throws Exception{
        return itripCommentMapper.getItripCommentListByMap(param);
    }

    public Integer getItripCommentCountByMap(Map<String,Object> param)throws Exception{
        return itripCommentMapper.getItripCommentCountByMap(param);
    }

    public Integer saveItripComment(ItripComment itripComment)throws Exception{
            itripComment.setCreationDate(new Date());
            return itripCommentMapper.saveItripComment(itripComment);
    }

    public Integer modifyItripComment(ItripComment itripComment)throws Exception{
        itripComment.setModifyDate(new Date());
        return itripCommentMapper.modifyItripComment(itripComment);
    }

    public Integer removeItripCommentById(Long id)throws Exception{
        return itripCommentMapper.removeItripCommentById(id);
    }

    public Page<ItripComment> queryItripCommentPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripCommentMapper.getItripCommentCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripComment> itripCommentList = itripCommentMapper.getItripCommentListByMap(param);
        page.setRows(itripCommentList);
        return page;
    }
}
