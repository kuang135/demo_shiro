package com.demo.shiro.spring.shiro;

import com.demo.shiro.spring.util.SpringUtil;
import com.octo.captcha.service.CaptchaService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/1/14.
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     *  表示当访问拒绝时是否已经处理了；如果返回 true 表示需要继续处理；
     *  如果返回 false 表示该拦截器实例已经处理了，将直接返回即可
     *
     *  重写，不保存之前的request，因为项目采用iframe，始终重定向到/admin/index.do
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //登陆地址
        if(this.isLoginRequest(request, response)) {
            //post方式
            if (this.isLoginSubmission(request, response)) {
                if (!checkValidateCode((HttpServletRequest) request)) {
                    return true;
                }
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        }
        //非登陆地址
        String XMLHttpRequest = ((HttpServletRequest) request).getHeader("X-Requested-With");
        //如果是ajax请求
        if (XMLHttpRequest != null && XMLHttpRequest.equals("XMLHttpRequest")) {
            ((HttpServletResponse)response).setHeader("sessionStatus", "timeOut");
        } else {
            //不是ajax请求
            String loginUrl = super.getLoginUrl();
            response.getWriter().write("<script>top.location.href = '" + loginUrl + "'</script>");
        }
        return false;
    }

    /**
     *  重写，密码进行md5
     */
    @Override
    protected String getPassword(ServletRequest request) {
        String passwordParam = this.getPasswordParam();
        String password = StringUtils.clean(request.getParameter(passwordParam));
        if (org.apache.commons.lang3.StringUtils.isNoneBlank(password)) {
            return new Md5Hash(password).toString();
        }
        return password;
    }

    private boolean checkValidateCode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String validateCode = request.getParameter("validateCode").toUpperCase();
        CaptchaService captchaService=(CaptchaService) SpringUtil.getBean("captchaService");
        boolean blnPass = false;
        try{
            blnPass = captchaService.validateResponseForID(session.getId(), validateCode);
        }catch(Exception ex){
            request.setAttribute("error", "验证码过期");
        }
        if ("".equalsIgnoreCase(validateCode) || !blnPass) {
            request.setAttribute("error", "验证码错误");
        }
        return blnPass;
    }
}
