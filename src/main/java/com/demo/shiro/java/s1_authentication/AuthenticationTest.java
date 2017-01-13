package com.demo.shiro.java.s1_authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * 1、首先调用 Subject.login(token)进行登录，其会自动委托给 SecurityManager，调用之前必须通过 SecurityUtils.setSecurityManager()设置；
 * 2、SecurityManager 负责真正的身份验证逻辑；它会委托给 Authenticator 进行身份验证；
 * 3、Authenticator 才是真正的身份验证者，Shiro API 中核心的身份认证入口点，此处可以自定义插入自己的实现；
 * 4、Authenticator 可能会委托给相应的 AuthenticationStrategy 进行多 Realm 身份验证，默认ModularRealmAuthenticator 会调用 AuthenticationStrategy 进行多 Realm 身份验证；
 * 5、Authenticator 会把相应的 token 传入 Realm，从 Realm 获取身份验证信息，如果没有返回/抛出异常表示身份验证失败了。此处可以配置多个 Realm，将按照相应的顺序及策略进行访问。
 */
public class AuthenticationTest {

    @Test
    public void loginSuccess() {
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:java/s1_authentication.ini");
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yao", "123");
        try {
            //4、登录，即身份验证，调用Realm的getAuthenticationInfo
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("--- 用户认证失败 ---");
        }
        System.out.println("用户已经认真: " + subject.isAuthenticated());
        //6、退出
        subject.logout();
    }

    @Test
    public void loginError() {
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:java/s1_authentication.ini");
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yao", "456");
        try {
            //4、登录，即身份验证，调用Realm的getAuthenticationInfo
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("--- 用户认证失败 ---");
        }
        System.out.println("用户已经认真: " + subject.isAuthenticated());
        //6、退出
        subject.logout();
    }
}
