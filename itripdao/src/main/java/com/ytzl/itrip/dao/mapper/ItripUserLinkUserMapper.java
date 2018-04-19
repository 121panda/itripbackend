package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripUserLinkUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripUserLinkUserMapper {

	public ItripUserLinkUser getItripUserLinkUserById(@Param(value = "id") Long id)throws Exception;

	public List<ItripUserLinkUser>	getItripUserLinkUserListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripUserLinkUserCountByMap(Map<String, Object> param)throws Exception;

	public Integer saveItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;

	public Integer modifyItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;

	public Integer removeItripUserLinkUserById(@Param(value = "id") Long id)throws Exception;

}
