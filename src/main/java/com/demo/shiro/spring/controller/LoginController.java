package com.demo.shiro.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author yaowk
 * @Date 2017/1/13 16:17
 */
@Controller
public class LoginController {

    @RequestMapping("login.do")
    public String login() {
        System.out.println("login");
        return "login";
    }


    @RequestMapping("logout.do")
    public String logout() {
        System.out.println("logout");
        return "login";
    }
}
