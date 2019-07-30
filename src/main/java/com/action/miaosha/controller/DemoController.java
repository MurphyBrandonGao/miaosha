package com.action.miaosha.controller;

import com.action.miaosha.result.CodeMsg;
import com.action.miaosha.result.Result;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dell
 * @create 2019-07-14 13:44
 */
@Controller
@EnableAutoConfiguration
public class DemoController {

    @ResponseBody
    String home() {
        return "Hello world";
    }

    @RequestMapping("hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello,imooc");
        // return new Result(0, "success", "hello,imooc");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_SERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "zhangsan");
        return "hello";
    }

}
