package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripHotelFeature;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelFeatureMapper {

	public ItripHotelFeature getItripHotelFeatureById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelFeature>	getItripHotelFeatureListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripHotelFeatureCountByMap(Map<String, Object> param)throws Exception;

	public Integer saveItripHotelFeature(ItripHotelFeature itripHotelFeature)throws Exception;

	public Integer modifyItripHotelFeature(ItripHotelFeature itripHotelFeature)throws Exception;

	public Integer removeItripHotelFeatureById(@Param(value = "id") Long id)throws Exception;

}
