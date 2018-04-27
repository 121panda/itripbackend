package com.ytzl.itrip.auth.controller;

import com.ytzl.itrip.auth.service.ItripHotelRoomService;
import com.ytzl.itrip.beans.model.ItripHotelRoom;
import com.ytzl.itrip.beans.vo.ItripHotelRoomVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sam on 2018/4/17.
 */
@Controller
// 对api的描述
@Api(description = "酒店房间管理模块")
@RequestMapping("/hotelRoom")
public class ItripHotelRoomController {

    @Resource
    private ItripHotelRoomService itripHotelRoomService;

    /*
    * value:接口说明
    * response:相应的数据类型
    * produces：返回的参数类型
    * httpMethod：提交方式
    * notes：发布说明
    * */
    @ApiOperation(
            value = "查询全部酒店房间信息",
            response = List.class,
            produces = "application/json",
            httpMethod = "get",
            notes = "查询全部酒店房间信息  \n" +
                    "　请求成功返回json数据,反之返回null")
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<ItripHotelRoom> queryAll() {
        Map<String, Object> param = new HashMap<>();
        try {
            return itripHotelRoomService.getItripHotelRoomListByMap(param);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation(
            value = "根据酒店id查询酒店房间信息",
            response = List.class,
            produces = "application/json",
            httpMethod = "post",
            notes = "根据酒店id查询酒店房间信息  \n" +
                    "　请求成功返回json数据,反之返回null")
    @RequestMapping(value = "/queryByHotelId", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<ItripHotelRoom> queryByHotelId(@RequestParam(value = "hotelId")
                                                   @ApiParam(value = "酒店id", required = true)
                                                           String hotelId) {
        Map<String, Object> param = new HashMap<>();
        param.put("hotelId", hotelId);
        try {
            return itripHotelRoomService.getItripHotelRoomListByMap(param);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @ApiOperation(
            value = "根据酒店id查询酒店房间信息",
            response = List.class,
            produces = "application/json",
            httpMethod = "post",
            notes = "根据酒店id查询酒店房间信息  \n" +
                    "　请求成功返回json数据,反之返回null")
    @RequestMapping(value = "/queryByItripHotelRoomVO", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<ItripHotelRoom> queryByItripHotelRoomVO(@RequestBody ItripHotelRoomVO itripHotelRoomVO) {
        Map<String, Object> param = new HashMap<>();
        param.put("hotelId", itripHotelRoomVO.getHotelId());
        try {
            return itripHotelRoomService.getItripHotelRoomListByMap(param);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
