package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripHotelTempStore;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelTempStoreMapper {

	public ItripHotelTempStore getItripHotelTempStoreById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelTempStore>	getItripHotelTempStoreListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripHotelTempStoreCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

	public Integer modifyItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

	public Integer removeItripHotelTempStoreById(@Param(value = "id") Long id)throws Exception;

}
