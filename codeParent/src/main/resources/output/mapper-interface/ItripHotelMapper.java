package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripHotel;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelMapper {

	public ItripHotel getItripHotelById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotel>	getItripHotelListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripHotelCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripHotel(ItripHotel itripHotel)throws Exception;

	public Integer modifyItripHotel(ItripHotel itripHotel)throws Exception;

	public Integer removeItripHotelById(@Param(value = "id") Long id)throws Exception;

}
