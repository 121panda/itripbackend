package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripHotelRoom;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelRoomMapper {

	public ItripHotelRoom getItripHotelRoomById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelRoom>	getItripHotelRoomListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripHotelRoomCountByMap(Map<String, Object> param)throws Exception;

	public Integer saveItripHotelRoom(ItripHotelRoom itripHotelRoom)throws Exception;

	public Integer modifyItripHotelRoom(ItripHotelRoom itripHotelRoom)throws Exception;

	public Integer removeItripHotelRoomById(@Param(value = "id") Long id)throws Exception;

}
