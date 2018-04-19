package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripHotelExtendProperty;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelExtendPropertyMapper {

	public ItripHotelExtendProperty getItripHotelExtendPropertyById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelExtendProperty>	getItripHotelExtendPropertyListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripHotelExtendPropertyCountByMap(Map<String, Object> param)throws Exception;

	public Integer saveItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception;

	public Integer modifyItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception;

	public Integer removeItripHotelExtendPropertyById(@Param(value = "id") Long id)throws Exception;

}
