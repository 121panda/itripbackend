package com.ytzl.itrip.auth.service.impl;
import com.ytzl.itrip.dao.mapper.ItripOrderLinkUserMapper;
import com.ytzl.itrip.beans.model.ItripOrderLinkUser;
import com.ytzl.itrip.utils.common.EmptyUtils;
import com.ytzl.itrip.utils.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.itrip.utils.common.Constants;
@Service("itripOrderLinkUserService")
public class ItripOrderLinkUserServiceImpl implements ItripOrderLinkUserService {

    @Resource
    private ItripOrderLinkUserMapper itripOrderLinkUserMapper;

    public ItripOrderLinkUser getItripOrderLinkUserById(Long id)throws Exception{
        return itripOrderLinkUserMapper.getItripOrderLinkUserById(id);
    }

    public List<ItripOrderLinkUser> getItripOrderLinkUserListByMap(Map<String,Object> param)throws Exception{
        return itripOrderLinkUserMapper.getItripOrderLinkUserListByMap(param);
    }

    public Integer getItripOrderLinkUserCountByMap(Map<String,Object> param)throws Exception{
        return itripOrderLinkUserMapper.getItripOrderLinkUserCountByMap(param);
    }

    public Integer saveItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception{
            itripOrderLinkUser.setCreationDate(new Date());
            return itripOrderLinkUserMapper.saveItripOrderLinkUser(itripOrderLinkUser);
    }

    public Integer modifyItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception{
        itripOrderLinkUser.setModifyDate(new Date());
        return itripOrderLinkUserMapper.modifyItripOrderLinkUser(itripOrderLinkUser);
    }

    public Integer removeItripOrderLinkUserById(Long id)throws Exception{
        return itripOrderLinkUserMapper.removeItripOrderLinkUserById(id);
    }

    public Page<ItripOrderLinkUser> queryItripOrderLinkUserPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripOrderLinkUserMapper.getItripOrderLinkUserCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripOrderLinkUser> itripOrderLinkUserList = itripOrderLinkUserMapper.getItripOrderLinkUserListByMap(param);
        page.setRows(itripOrderLinkUserList);
        return page;
    }
}
