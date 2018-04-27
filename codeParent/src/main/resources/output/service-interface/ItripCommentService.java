package com.ytzl.itrip.auth.service;
import com.ytzl.itrip.beans.model.ItripComment;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripCommentService {

    public ItripComment getItripCommentById(Long id)throws Exception;

    public List<ItripComment>	getItripCommentListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripCommentCountByMap(Map<String,Object> param)throws Exception;

    public Integer saveItripComment(ItripComment itripComment)throws Exception;

    public Integer modifyItripComment(ItripComment itripComment)throws Exception;

    public Integer removeItripCommentById(Long id)throws Exception;

    public Page<ItripComment> queryItripCommentPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
