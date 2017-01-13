package com.demo.shiro.java.s1_authentication;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @Author yaowk
 * @Date 2017/1/13 13:48
 */
public class MyRealm implements Realm{
    //返回一个唯一的 Realm 名字
    @Override
    public String getName() {
        return "MyReam";
    }

    //判断此 Realm 是否支持此 Token
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持 UsernamePasswordToken 类型的 Token
        return token instanceof UsernamePasswordToken;
    }

    //根据 Token 获取认证信息
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal(); //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"yao".equals(username)) {
            System.out.println("MyRealm ------------- 用户名错误" );
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            System.out.println("MyRealm ------------- 密码错误" );
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
        System.out.println("MyRealm ------------- 认证成功" );
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
