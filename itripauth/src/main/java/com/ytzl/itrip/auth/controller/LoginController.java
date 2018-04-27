package com.ytzl.itrip.auth.controller;

import com.ytzl.itrip.auth.service.ItripUserService;
import com.ytzl.itrip.auth.service.TokenService;
import com.ytzl.itrip.beans.model.ItripUser;
import com.ytzl.itrip.beans.vo.ItripUserTokenVO;
import com.ytzl.itrip.utils.common.Dto;
import com.ytzl.itrip.utils.common.DtoUtil;
import com.ytzl.itrip.utils.common.ErrorCode;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
@Controller
@RequestMapping("/api")
@Api(description = "登录模块控制器")
public class LoginController {
    @Resource
    private ItripUserService itripUserService;
    @Resource
    private TokenService tokenService;

    @ApiOperation(
            value = "登录",
            httpMethod = "post",
            produces = "application/json",
            response = Dto.class,
            notes = "根据用户名、密码进行统一验证"
    )
    @RequestMapping(value = "/dologin", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Dto doLogin(@RequestParam("name") @ApiParam(value = "用户账号", required = true) String name
            , @RequestParam("password") @ApiParam(value = "用户密码", required = true) String password
            , HttpServletRequest request) {
        /**
         * 调用service login方法登录并获取用户信息
         *  构建token
         *  保存token
         *  返回客户端token
         *
         *
         *  返回错误信息
         * */
        try {
            ItripUser itripUser = itripUserService.login(name, password);
            String userAgent = request.getHeader("user-agent"); // 获取请求头部信息
            String token = tokenService.generateToken(userAgent, itripUser);
            tokenService.saveToken(token, itripUser);
            // 返回客户端
            // 获取当前时间
            Long genTime = Calendar.getInstance().getTimeInMillis();
            ItripUserTokenVO itripUserTokenVO = new ItripUserTokenVO(token, genTime + 60 * 60 * 2 * 1000, genTime);
            return DtoUtil.returnDataSuccess(itripUserTokenVO);
        } catch (ItripExcepion itripExcepion) {
            itripExcepion.printStackTrace();
            return DtoUtil.returnFail(itripExcepion.getMessage(), itripExcepion.getErrorCode());
        }
    }
    @ApiOperation(
            value = "退出",
            httpMethod = "get",
            produces = "application/json",
            response = Dto.class,
            notes = "客户端需要在header中发送token"
    )
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json", headers = "token")
    // 指定参数说明
    @ApiImplicitParam(required = true, value = "唯一凭证", name = "token", paramType = "header")
    @ResponseBody
    public Dto logOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        // 判断token是否有效
        String userAgent = request.getHeader("user-agent");
        if(tokenService.validateToken(token, userAgent)){
            // 有效则删除token，并返回退出成功
            tokenService.removeToken(token);
            return DtoUtil.returnSuccess("注销成功");
        } else {
            // 否则返回退出失败token无效
            return DtoUtil.returnFail("token无效", ErrorCode.AUTH_TOKEN_INVALID);
        }
    }
}
