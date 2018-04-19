package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripAreaDic;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripAreaDicMapper {

	public ItripAreaDic getItripAreaDicById(@Param(value = "id") Long id)throws Exception;

	public List<ItripAreaDic>	getItripAreaDicListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripAreaDicCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripAreaDic(ItripAreaDic itripAreaDic)throws Exception;

	public Integer modifyItripAreaDic(ItripAreaDic itripAreaDic)throws Exception;

	public Integer removeItripAreaDicById(@Param(value = "id") Long id)throws Exception;

}
