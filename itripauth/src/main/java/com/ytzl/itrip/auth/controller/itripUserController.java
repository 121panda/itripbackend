package com.ytzl.itrip.auth.controller;

import com.ytzl.itrip.auth.service.ItripUserService;
import com.ytzl.itrip.auth.service.MessageService;
import com.ytzl.itrip.beans.model.ItripUser;
import com.ytzl.itrip.beans.vo.ItripUserRegisterVO;
import com.ytzl.itrip.utils.common.DigestUtil;
import com.ytzl.itrip.utils.common.Dto;
import com.ytzl.itrip.utils.common.DtoUtil;
import com.ytzl.itrip.utils.common.ErrorCode;
import com.ytzl.itrip.utils.exception.ItripExcepion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/24 0024.
 */
@Controller
@RequestMapping("/api")
@Api(description = "用户模块")
public class itripUserController {
    @Resource
    private ItripUserService itripUserService;

    @ApiOperation(
            value = "用户名验证",
            httpMethod = "GET",
            produces = "application/json",
            response = Dto.class,
            notes = "验证该用户是否已存在"
    )
    @RequestMapping(value = "/ckusr", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Dto ckusr(@RequestParam("name")
                       @ApiParam(value = "用户账号", required = true) String name) {
        try {
            ItripUser itripUser = itripUserService.getItripUserByUserCode(name);
            if (null == itripUser) {
                return DtoUtil.returnSuccess("账号可用");
            } else {
                return DtoUtil.returnFail("用户已存在", ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }
        } catch (ItripExcepion itripExcepion) {
            itripExcepion.printStackTrace();
            return DtoUtil.returnFail("程序异常", ErrorCode.AUTH_UNKNOWN);
        }
    }

    @ApiOperation(
            value = "使用手机注册",
            httpMethod = "POST",
            produces = "application/json",
            response = Dto.class,
            notes = "使用手机注册"
    )
    @RequestMapping(value = "/registerbyphone", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Dto registerByPhone(@RequestBody ItripUserRegisterVO itripUserRegisterVO) {
        System.out.println(itripUserRegisterVO.getUserCode());
        // 验证手机号是否正确
        System.out.println(itripUserRegisterVO.getUserCode());
        if (!validPhone(itripUserRegisterVO.getUserCode())) {
            return DtoUtil.returnFail("手机号格式不正确", ErrorCode.AUTH_UNKNOWN);
        }
        // 将vo类转换为实体类
        ItripUser itripUser = new ItripUser();
        itripUser.setUserCode(itripUserRegisterVO.getUserCode());
        itripUser.setUserName(itripUserRegisterVO.getUserName());
        // 加密
        itripUser.setUserPassword(DigestUtil.hmacSign(itripUserRegisterVO.getUserPassword()));
        // 调用service进行注册
        try {
            ItripUser user = itripUserService.getItripUserByUserCode(itripUser.getUserCode());
            if(null == user) {
                itripUserService.registerByPhone(itripUser);
                return DtoUtil.returnSuccess();
            } else {
                throw new ItripExcepion("用户已存在",ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }

        } catch (ItripExcepion itripExcepion) {
            itripExcepion.printStackTrace();
            return DtoUtil.returnFail(itripExcepion.getMessage(), itripExcepion.getErrorCode());
        }
    }

    @ApiOperation(
            value = "手机号注册短信验证",
            httpMethod = "PUT",
            produces = "application/json",
            response = Dto.class,
            notes = "手机号注册短信验证"
    )
    @RequestMapping(value = "/validatephone", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Dto validatePhone(@RequestParam("user")
                       @ApiParam(value = "用户账号", required = true) String user,
                       @RequestParam("code")
                       @ApiParam(value = "验证码", required = true) String code) {
        try {
            // 验证短信验证码
            itripUserService.activatePhone(user,code);
            // 验证成功
            return DtoUtil.returnSuccess("验证成功");
        } catch (ItripExcepion itripExcepion) {
            itripExcepion.printStackTrace();
            return DtoUtil.returnSuccess("验证失败");
        }

    }
    @Resource
    private MessageService messageService;
    @ApiOperation(
            value = "邮箱验证",
            httpMethod = "PUT",
            produces = "application/json",
            response = Dto.class,
            notes = "邮箱验证"
    )
    @RequestMapping(value = "/activate", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Dto activate(@RequestParam("user")
                             @ApiParam(value = "用户账号", required = true) String user,
                             @RequestParam("code")
                             @ApiParam(value = "验证码", required = true) String code) {
            // 邮箱验证码
        try {
            itripUserService.activate(user, code);
            return DtoUtil.returnSuccess("验证成功");
        } catch (ItripExcepion itripExcepion) {
            itripExcepion.printStackTrace();
            return DtoUtil.returnFail("验证失败",ErrorCode.AUTH_UNKNOWN);
        }
    }
    @ApiOperation(
            value = "使用邮箱注册",
            httpMethod = "POST",
            produces = "application/json",
            response = Dto.class,
            notes = "使用邮箱注册"
    )
    @RequestMapping(value = "/doregister", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Dto doregister(@RequestBody ItripUserRegisterVO itripUserRegisterVO) {
        System.out.println(itripUserRegisterVO.getUserCode());
        // 验证邮箱验证码是否正确
//        System.out.println(itripUserRegisterVO.getUserCode());
        if (!validEmail(itripUserRegisterVO.getUserCode())) {
            return DtoUtil.returnFail("邮箱格式格式不正确", ErrorCode.AUTH_UNKNOWN);
        }
        // 将vo类转换为实体类
        ItripUser itripUser = new ItripUser();
        itripUser.setUserCode(itripUserRegisterVO.getUserCode());
        itripUser.setUserName(itripUserRegisterVO.getUserName());
        // 加密
        itripUser.setUserPassword(DigestUtil.hmacSign(itripUserRegisterVO.getUserPassword()));
        // 调用service进行注册
        try {
            ItripUser user = itripUserService.getItripUserByUserCode(itripUser.getUserCode());
            if(null == user) {
                itripUserService.doregister(itripUser);
                return DtoUtil.returnSuccess();
            } else {
                throw new ItripExcepion("用户已存在",ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }

        } catch (ItripExcepion itripExcepion) {
            itripExcepion.printStackTrace();
            return DtoUtil.returnFail(itripExcepion.getMessage(), itripExcepion.getErrorCode());
        }
    }
    /**
     * 合法E-mail地址：
     * 1. 必须包含一个并且只有一个符号“@”
     * 2. 第一个字符不得是“@”或者“.”
     * 3. 不允许出现“@.”或者.@
     * 4. 结尾不得是字符“@”或者“.”
     * 5. 允许“@”前的字符中出现“＋”
     * 6. 不允许“＋”在最前面，或者“＋@”
     */
    private boolean validEmail(String email) {

        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return Pattern.compile(regex).matcher(email).find();
    }

    /**
     * 验证是否合法的手机号
     *
     * @param phone
     * @return
     */
    private boolean validPhone(String phone) {
        String regex = "^1[3578]{1}\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }
}
