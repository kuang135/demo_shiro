package com.demo.shiro.spring.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Administrator on 2017/1/14.
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     *  重写，不保存之前的request，因为项目采用iframe，始终重定向到/admin/index.do
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(this.isLoginRequest(request, response)) {
            if(this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            //this.saveRequestAndRedirectToLogin(request, response);
            this.redirectToLogin(request, response);
            return false;
        }
    }

    /**
     *  重写，密码进行md5
     */
    @Override
    protected String getPassword(ServletRequest request) {
        String passwordParam = this.getPasswordParam();
        String password = StringUtils.clean(request.getParameter(passwordParam));
        String md5Password = new Md5Hash(password).toString();
        return md5Password;
    }
}
