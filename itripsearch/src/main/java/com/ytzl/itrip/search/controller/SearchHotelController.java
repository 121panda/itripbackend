package com.ytzl.itrip.search.controller;

import com.ytzl.itrip.beans.vo.SearchHotCityVO;
import com.ytzl.itrip.beans.vo.SearchHotelVO;
import com.ytzl.itrip.search.beans.Hotel;
import com.ytzl.itrip.search.service.SeachHotelService;
import com.ytzl.itrip.utils.common.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */
@Controller
@RequestMapping("/api/hotellist")
@Api(description = "酒店查询模块")
public class SearchHotelController {
    @Resource
    private SeachHotelService seachHotelService;
    @ApiOperation(value = "查询酒店分页",
            httpMethod = "Post",
            response = Dto.class,
            produces = "application/json",
            notes = "查询酒店分页(用于查询酒店列表)  \\n\" +\n" +
                    "        \"成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：  \\n\" +\n" +
                    "        \"错误码:  \\n\" +\n" +
                    "        \"20001: 系统异常,获取失败  \\n\" +\n" +
                    "        \"20002: 目的地不能为空")
    @RequestMapping(value = "/searchItripHotelPage",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public Dto searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO){
        // 判断目的地是否为空
        if(EmptyUtils.isEmpty(searchHotelVO.getDestination())) {
            return DtoUtil.returnFail("目的地不能为空",
                    ErrorCode.SEARCH_ITRIP_HOTEL_PAGE_DESTINATION_FAIL);
        }
        try {
            Page<Hotel> page = seachHotelService.searchHotelPage(searchHotelVO);
            return DtoUtil.returnDataSuccess(page);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取失败",
                    ErrorCode.SEARCH_ITRIP_HOTEL_PAGE_FAIL);
        }
    }
    @ApiOperation(value = "根据热门城市查询酒店",
            httpMethod = "Post",
            response = Dto.class,
            produces = "application/json",
            notes = "根据热门城市查询酒店  \\n\" +\n" +
                    "        \"成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：  \\n\" +\n" +
                    "        \"错误码:  \\n\" +\n" +
                    "        \"20003: 系统异常,获取失败  \\n\" +\n" +
                    "        \"20004: 城市id不能为空")
    @RequestMapping(value = "/searchItripHotelListByHotCity",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public Dto searchItripHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO){
        if(EmptyUtils.isEmpty(searchHotCityVO.getCityId())) {
            return DtoUtil.returnFail("城市id不能为空",
                    ErrorCode.SEARCH_ITRIP_HOTEL_LIST_BY_HOT_CITY_CITYID_FAIL);
        }
        try {
            List<Hotel> hotelList = seachHotelService.searchHotelByHotCity(searchHotCityVO);
            return DtoUtil.returnDataSuccess(hotelList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取失败",
                    ErrorCode.SEARCH_ITRIP_HOTEL_LIST_BY_HOT_CITY_FAIL);
        }
    }
}
