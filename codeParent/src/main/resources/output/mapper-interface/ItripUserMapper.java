package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripUserMapper {

	public ItripUser getItripUserById(@Param(value = "id") Long id)throws Exception;

	public List<ItripUser>	getItripUserListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripUserCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripUser(ItripUser itripUser)throws Exception;

	public Integer modifyItripUser(ItripUser itripUser)throws Exception;

	public Integer removeItripUserById(@Param(value = "id") Long id)throws Exception;

}
