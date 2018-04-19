package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripProductStore;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripProductStoreMapper {

	public ItripProductStore getItripProductStoreById(@Param(value = "id") Long id)throws Exception;

	public List<ItripProductStore>	getItripProductStoreListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripProductStoreCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripProductStore(ItripProductStore itripProductStore)throws Exception;

	public Integer modifyItripProductStore(ItripProductStore itripProductStore)throws Exception;

	public Integer removeItripProductStoreById(@Param(value = "id") Long id)throws Exception;

}
