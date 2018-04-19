package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripComment;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripCommentMapper {

	public ItripComment getItripCommentById(@Param(value = "id") Long id)throws Exception;

	public List<ItripComment>	getItripCommentListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripCommentCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripComment(ItripComment itripComment)throws Exception;

	public Integer modifyItripComment(ItripComment itripComment)throws Exception;

	public Integer removeItripCommentById(@Param(value = "id") Long id)throws Exception;

}
