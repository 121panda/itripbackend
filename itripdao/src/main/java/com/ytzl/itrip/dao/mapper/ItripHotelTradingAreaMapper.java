package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripHotelTradingArea;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelTradingAreaMapper {

	public ItripHotelTradingArea getItripHotelTradingAreaById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelTradingArea>	getItripHotelTradingAreaListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripHotelTradingAreaCountByMap(Map<String, Object> param)throws Exception;

	public Integer saveItripHotelTradingArea(ItripHotelTradingArea itripHotelTradingArea)throws Exception;

	public Integer modifyItripHotelTradingArea(ItripHotelTradingArea itripHotelTradingArea)throws Exception;

	public Integer removeItripHotelTradingAreaById(@Param(value = "id") Long id)throws Exception;

}
