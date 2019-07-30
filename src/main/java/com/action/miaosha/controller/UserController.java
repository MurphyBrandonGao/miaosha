package com.action.miaosha.controller;

import com.action.miaosha.domain.MiaoshaUser;
import com.action.miaosha.redis.RedisService;
import com.action.miaosha.result.Result;
import com.action.miaosha.service.MiaoshaUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dell
 * @create 2019-07-21 20:59
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }

}
