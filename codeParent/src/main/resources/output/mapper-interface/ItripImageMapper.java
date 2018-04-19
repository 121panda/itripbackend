package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripImage;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripImageMapper {

	public ItripImage getItripImageById(@Param(value = "id") Long id)throws Exception;

	public List<ItripImage>	getItripImageListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripImageCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripImage(ItripImage itripImage)throws Exception;

	public Integer modifyItripImage(ItripImage itripImage)throws Exception;

	public Integer removeItripImageById(@Param(value = "id") Long id)throws Exception;

}
