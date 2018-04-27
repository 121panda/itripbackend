package com.ytzl.itrip.auth.controller;

import com.ytzl.itrip.auth.service.TokenService;
import com.ytzl.itrip.beans.model.ItripUser;
import com.ytzl.itrip.beans.vo.ItripUserTokenVO;
import com.ytzl.itrip.utils.common.Dto;
import com.ytzl.itrip.utils.common.DtoUtil;
import com.ytzl.itrip.utils.exception.ItripExcepion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
@Controller
@RequestMapping("/api")
@Api(description = "维护token")
public class TokenController {
    @Resource
    private TokenService tokenService;
    @ApiOperation(
            value = "Token置换",
            httpMethod = "post",
            produces = "application/json",
            response = Dto.class,
            notes = "提供客户端置换token操作，服务器需要获取客户端header中的token串"
    )
    @RequestMapping(value = "/retoken", method = RequestMethod.POST, produces = "application/json", headers = "token")
    // 指定参数说明
    @ApiImplicitParam(required = true, value = "唯一凭证", name = "token", paramType = "header")
    @ResponseBody
    public Dto retoken(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userAgent = request.getHeader("user-agent");
        try {
            // 重置token
            String retoken = tokenService.retoken(userAgent, token);
            // 返回客户端
            // 获取当前时间
            Long genTime = Calendar.getInstance().getTimeInMillis();
            ItripUserTokenVO itripUserTokenVO = new ItripUserTokenVO(retoken, genTime + 60 * 60 * 2 * 1000, genTime);
            return DtoUtil.returnDataSuccess(itripUserTokenVO);
        } catch (ItripExcepion itripExcepion) {
            itripExcepion.printStackTrace();
            return DtoUtil.returnFail(itripExcepion.getMessage(), itripExcepion.getErrorCode());
        }
    }
}
