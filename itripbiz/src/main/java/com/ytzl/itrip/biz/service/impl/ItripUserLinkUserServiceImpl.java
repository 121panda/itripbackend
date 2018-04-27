package com.ytzl.itrip.biz.service.impl;
import com.ytzl.itrip.biz.service.ItripUserLinkUserService;
import com.ytzl.itrip.dao.mapper.ItripUserLinkUserMapper;
import com.ytzl.itrip.beans.model.ItripUserLinkUser;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripUserLinkUserService")
public class ItripUserLinkUserServiceImpl implements ItripUserLinkUserService {

    @Resource
    private ItripUserLinkUserMapper itripUserLinkUserMapper;

    public ItripUserLinkUser getItripUserLinkUserById(Long id)throws Exception{
        return itripUserLinkUserMapper.getItripUserLinkUserById(id);
    }

    public List<ItripUserLinkUser> getItripUserLinkUserListByMap(Map<String,Object> param)throws Exception{
        return itripUserLinkUserMapper.getItripUserLinkUserListByMap(param);
    }

    public Integer getItripUserLinkUserCountByMap(Map<String,Object> param)throws Exception{
        return itripUserLinkUserMapper.getItripUserLinkUserCountByMap(param);
    }

    public Integer saveItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception{
            itripUserLinkUser.setCreationDate(new Date());
            return itripUserLinkUserMapper.saveItripUserLinkUser(itripUserLinkUser);
    }

    public Integer modifyItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception{
        itripUserLinkUser.setModifyDate(new Date());
        return itripUserLinkUserMapper.modifyItripUserLinkUser(itripUserLinkUser);
    }

    public Integer removeItripUserLinkUserById(Long id)throws Exception{
        return itripUserLinkUserMapper.removeItripUserLinkUserById(id);
    }

    public Page<ItripUserLinkUser> queryItripUserLinkUserPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripUserLinkUserMapper.getItripUserLinkUserCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripUserLinkUser> itripUserLinkUserList = itripUserLinkUserMapper.getItripUserLinkUserListByMap(param);
        page.setRows(itripUserLinkUserList);
        return page;
    }
}
