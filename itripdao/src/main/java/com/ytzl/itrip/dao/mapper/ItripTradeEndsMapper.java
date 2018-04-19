package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripTradeEnds;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripTradeEndsMapper {

	public ItripTradeEnds getItripTradeEndsById(@Param(value = "id") Long id)throws Exception;

	public List<ItripTradeEnds>	getItripTradeEndsListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripTradeEndsCountByMap(Map<String, Object> param)throws Exception;

	public Integer saveItripTradeEnds(ItripTradeEnds itripTradeEnds)throws Exception;

	public Integer modifyItripTradeEnds(ItripTradeEnds itripTradeEnds)throws Exception;

	public Integer removeItripTradeEndsById(@Param(value = "id") Long id)throws Exception;

}
