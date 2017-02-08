package com.demo.shiro.spring.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yaowk
 * @Date 2017/1/13 16:17
 */
@Controller
public class LoginController {

    /**
     * FormAuthenticationFilter onAccessDenied的逻辑
     *      如果不是登录url，保存当前request，并重定向到登录url，返回false，不需要继续处理
     *      如果是登录url，
     *          不为POST，返回true，继续处理（当前方法）
     *          为POST，通过realm身份认证
     *              认证成功，返回true，继续处理（返回之前的request）
     *              认证失败，返回false，不需要继续处理
     *  项目中使用iframe，根据需求改写 FormAuthenticationFilter
     *      如果是其他路径，重定向到login.do
     *      如果是做登录路径，认证成功，重定向到index.do
     *      如果是做登录路径，认证失败，返回login.jsp，放入登录异常信息
     */
    @RequestMapping("/admin/login")
    public String login(HttpServletRequest request) {
        //已经登录，直接跳转
        Subject subject = SecurityUtils.getSubject();
        if(subject != null && subject.isAuthenticated()){
            return "redirect:/admin/index.do";
        }
        String errorClassName = (String)request.getAttribute("shiroLoginFailure");
        System.out.println(errorClassName);
        if(UnknownAccountException.class.getName().equals(errorClassName)) {
            request.setAttribute("error", "用户名/密码错误");
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            request.setAttribute("error", "用户名/密码错误");
        } else if(errorClassName != null) {
            request.setAttribute("error", "未知错误：" + errorClassName);
        }
        return "login";
    }


    @RequestMapping("/admin/index")
    public String index() {
        System.out.println("------- index -------");
        return "index";
    }

    @RequestMapping("/admin/other")
    public String other() {
        System.out.println("------- other -------");
        return "other";
    }

    @RequestMapping("/admin/ajax")
    @ResponseBody
    public Map<String,String> ajax() {
        System.out.println("------- ajax -------");
        Map<String,String> data = new HashMap<>();
        data.put("msg","success");
        return data;
    }

}
