package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripLabelDic;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripLabelDicMapper {

	public ItripLabelDic getItripLabelDicById(@Param(value = "id") Long id)throws Exception;

	public List<ItripLabelDic>	getItripLabelDicListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripLabelDicCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

	public Integer modifyItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

	public Integer removeItripLabelDicById(@Param(value = "id") Long id)throws Exception;

}
