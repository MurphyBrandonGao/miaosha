package com.action.miaosha.controller;

import com.action.miaosha.domain.User;
import com.action.miaosha.rabbitmq.MQSender;
import com.action.miaosha.redis.RedisService;
import com.action.miaosha.redis.UserKey;
import com.action.miaosha.result.Result;
import com.action.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dell
 * @create 2019-07-14 0:17
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    /*@RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header() {
		sender.sendHeader("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout() {
		sender.sendFanout("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic() {
		sender.sendTopic("hello,imooc");
        return Result.success("Hello，world");
    }

    @RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq() {
        sender.send("hello sender");
        return Result.success("Hello,world");
    }*/

    @RequestMapping("/home")
    @ResponseBody
    public Result<String> home() {
        return Result.success("Hello,world");
    }

    @RequestMapping("/hello/themaleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "zhangsan");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById, "" + 1, user); // Userkey:id1
        return Result.success(true);
    }
}
