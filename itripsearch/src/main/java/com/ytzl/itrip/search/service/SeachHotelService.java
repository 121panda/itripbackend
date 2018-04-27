package com.ytzl.itrip.search.service;

import com.ytzl.itrip.beans.vo.SearchHotCityVO;
import com.ytzl.itrip.beans.vo.SearchHotelVO;
import com.ytzl.itrip.search.beans.Hotel;
import com.ytzl.itrip.utils.common.Page;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */
public interface SeachHotelService {
    /**
     * 查询酒店分页
     * @return
     */
    public Page<Hotel> searchHotelPage(SearchHotelVO searchHotelVO) throws Exception;

    /**
     * 根据热门城市查询酒店列表
     * @return
     */
    public List<Hotel> searchHotelByHotCity(SearchHotCityVO searchHotCityVO) throws Exception;
}
