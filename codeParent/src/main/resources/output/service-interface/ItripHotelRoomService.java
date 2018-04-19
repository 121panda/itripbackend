package com.ytzl.itrip.service;
import com.ytzl.itrip.beans.model.ItripHotelRoom;
import java.util.List;
import java.util.Map;

import com.ytzl.itrip.utils.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripHotelRoomService {

    public ItripHotelRoom getItripHotelRoomById(Long id)throws Exception;

    public List<ItripHotelRoom>	getItripHotelRoomListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelRoomCountByMap(Map<String,Object> param)throws Exception;

    public Integer saveItripHotelRoom(ItripHotelRoom itripHotelRoom)throws Exception;

    public Integer modifyItripHotelRoom(ItripHotelRoom itripHotelRoom)throws Exception;

    public Integer removeItripHotelRoomById(Long id)throws Exception;

    public Page<ItripHotelRoom> queryItripHotelRoomPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
