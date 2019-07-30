package com.action.miaosha.controller;

import com.action.miaosha.redis.RedisService;
import com.action.miaosha.result.Result;
import com.action.miaosha.service.MiaoshaUserService;
import com.action.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Dell
 * @create 2019-07-14 0:17
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    public static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, LoginVo loginVo) {
        log.info(loginVo.toString());
        //参数校验
        /*String password = loginVo.getPassword();
        String mobie = loginVo.getMobile();
        if (StringUtils.isEmpty(password))
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        if (StringUtils.isEmpty(mobie))
            return Result.error(CodeMsg.MOBILE_EMPTY);
        if (ValidatorUtil.isMobile(mobie))
            return Result.error(CodeMsg.MOBILE_ERROR);*/

        // 登录
        miaoshaUserService.login(response, loginVo);
        return Result.success(true);
    }

}
