package com.ytzl.itrip.dao.mapper;
import com.ytzl.itrip.beans.model.ItripOrderLinkUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripOrderLinkUserMapper {

	public ItripOrderLinkUser getItripOrderLinkUserById(@Param(value = "id") Long id)throws Exception;

	public List<ItripOrderLinkUser>	getItripOrderLinkUserListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripOrderLinkUserCountByMap(Map<String,Object> param)throws Exception;

	public Integer saveItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception;

	public Integer modifyItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception;

	public Integer removeItripOrderLinkUserById(@Param(value = "id") Long id)throws Exception;

}
