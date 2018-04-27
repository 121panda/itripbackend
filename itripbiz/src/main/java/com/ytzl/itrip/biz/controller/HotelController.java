package com.ytzl.itrip.biz.controller;

import com.sun.org.apache.xalan.internal.xsltc.trax.DOM2TO;
import com.ytzl.itrip.beans.model.ItripAreaDic;
import com.ytzl.itrip.beans.model.ItripLabelDic;
import com.ytzl.itrip.beans.vo.ItripAreaDicHotVo;
import com.ytzl.itrip.beans.vo.ItripLabelDicVO;
import com.ytzl.itrip.biz.service.ItripAreaDicService;
import com.ytzl.itrip.biz.service.ItripLabelDicService;
import com.ytzl.itrip.utils.common.Dto;
import com.ytzl.itrip.utils.common.DtoUtil;
import com.ytzl.itrip.utils.common.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.security.action.PutAllAction;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/25 0025.
 */
@Controller
@RequestMapping("/api/hotel")
@Api(description = "酒店业务模块")
public class HotelController {
    @Resource
    private ItripAreaDicService itripAreaDicService;
    @Resource
    private ItripLabelDicService itripLabelDicService;

    @ApiOperation(value = "查询(国内，国外) 热门城市",
            httpMethod = "GET",
            produces = "application/json",
            response = Dto.class,
            notes = "查询国内、国外的热门城市(1:国内 2:国外) 成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：   \\n\" +\n" +
                    "        \"错误码：  \\n\" +\n" +
                    "        \"10202 : 系统异常,获取失败")
    @RequestMapping(value = "/queryhotcity/{type}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Dto queryhotcity(@PathVariable(value = "type")
                            @ApiParam(value = "类型", required = true) int type) {
        // isHot  1热门城市
        //isChina 1国内  2国外
        Map<String, Object> param = new HashMap<>();
        param.put("isHot", 1);
        param.put("isChina", type);
        List<ItripAreaDicHotVo> itripAreaDicHotVoList = new ArrayList<>();
        try {
            List<ItripAreaDic> itripAreaDicList = itripAreaDicService.getItripAreaDicListByMap(param);
            // 遍历返回结果，将实体类转换成vo类
            for (ItripAreaDic itripAreaDic : itripAreaDicList) {
                ItripAreaDicHotVo itripAreaDicHotVo = new ItripAreaDicHotVo();
                // 自动复制 字段名称类型都一致   第一个参数为有值对象，第二个参数为将要被赋值的对象
                BeanUtils.copyProperties(itripAreaDic, itripAreaDicHotVo);
                itripAreaDicHotVoList.add(itripAreaDicHotVo);
            }
            // 返回vo集合
            return DtoUtil.returnDataSuccess(itripAreaDicHotVoList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取失败", ErrorCode.BIZ__HOTEL_QUERY_HOTCITY_FAIL);
        }
    }

    @ApiOperation(value = "查询酒店特色列表",
            httpMethod = "GET",
            produces = "application/json",
            response = Dto.class,
            notes = "获取酒店特色(用于查询页列表)  \\n\" +\n" +
                    "        \"成功：success = ‘true’ | 失败：success = ‘false’ \\n\" +\n" +
                    "        \"并返回错误码，如下：  错误码:  \\n\" +\n" +
                    "        \"10205: 系统异常,获取失败")
    @RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Dto queryhotelfeature() {
        Map<String, Object> param = new HashMap<>();
        param.put("parentId", "16");
        List<ItripLabelDicVO> itripLabelDicVOList = new ArrayList<>();
        try {
            // 查询酒店特色列表
            List<ItripLabelDic> itripLabelDicList
                    = itripLabelDicService.getItripLabelDicListByMap(param);
            for (ItripLabelDic itripLabelDic : itripLabelDicList) {
                ItripLabelDicVO itripLabelDicVO = new ItripLabelDicVO();
                BeanUtils.copyProperties(itripLabelDic, itripLabelDicVO);
                itripLabelDicVOList.add(itripLabelDicVO);
            }
            return DtoUtil.returnDataSuccess(itripLabelDicVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取失败", ErrorCode.BIZ_HOTEL_QYERY_HOTEL_FEATURE_FAIL);
        }
    }
    @ApiOperation(value = "根据城市查询商圈",
            httpMethod = "GET",
            produces = "application/json",
            response = Dto.class,
            notes = "根据城市查询商圈  \\n\" +\n" +
                    "        \"成功：success = ‘true’ | 失败：success = ‘false’ \\n\" +\n" +
                    "        \"并返回错误码，如下：  \\n\" +\n" +
                    "        \"错误码：\n"+
                    "        \"10204 : 系统异常,获取失败")
    @RequestMapping(value = "/querytradearea/{cityId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Dto querytradearea(@PathVariable(value = "cityId")
                            @ApiParam(value = "城市id", required = true) int cityId) {
        Map<String, Object> param = new HashMap<>();
        param.put("parent", cityId);
        // ==1 商圈
        param.put("isTradingArea", "1");
        List<ItripAreaDicHotVo> itripAreaDicHotVoList = new ArrayList<>();
        try {
            List<ItripAreaDic> itripAreaDicList = itripAreaDicService.getItripAreaDicListByMap(param);
            // 遍历返回结果，将实体类转换成vo类
            for (ItripAreaDic itripAreaDic : itripAreaDicList) {
                ItripAreaDicHotVo itripAreaDicHotVo = new ItripAreaDicHotVo();
                // 自动复制 字段名称类型都一致   第一个参数为有值对象，第二个参数为将要被赋值的对象
                BeanUtils.copyProperties(itripAreaDic, itripAreaDicHotVo);
                itripAreaDicHotVoList.add(itripAreaDicHotVo);
            }
            return DtoUtil.returnDataSuccess(itripAreaDicHotVoList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取失败", ErrorCode.BIZ_HOTEL_QUERY_HOTEL_TRADE_AREA_FAIL);
        }

    }
}
