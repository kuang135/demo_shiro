package com.demo.shiro.java.s2_authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * 1、首先调用 Subject.isPermitted/hasRole接口，其会委托给 SecurityManager，而SecurityManager 接着会委托给 Authorizer；
 * 2、Authorizer 是真正的授权者，如果我们调用如 isPermitted("user:view")，其首先会通过PermissionResolver 把字符串转换成相应的 Permission 实例；
 * 3、在进行授权之前，其会调用相应的 Realm 获取 Subject 相应的角色/权限用于匹配传入的角色/权限；
 * 4、Authorizer 会判断 Realm 的角色/权限是否和传入的匹配，如果有多个 Realm，会委托给ModularRealmAuthorizer 进行循环判断，如果匹配如 isPermitted/hasRole会返回 true，否则返回 false 表示授权失败。
 */
public class AuthorizationTest {

    @Test
    public void role() {
        //登陆
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:java/s2_authorization.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yao", "123");
        subject.login(token);

        //角色
        boolean role1 = subject.hasRole("role1");
        boolean role1AndRole2 = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("has role1: " + role1);
        System.out.println("has role1 and role2: " + role1AndRole2);
        boolean[] roles = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        for (int i = 1; i <= roles.length; i++) {
            System.out.println("has role" + i + ": " + roles[i-1]);
        }
    }

    @Test
    public void permission() {
        //登陆
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:java/s2_authorization.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yao", "123");
        subject.login(token);

        //权限
        boolean user1Update = subject.isPermitted("user1:update");
        boolean user1Delete = subject.isPermitted("user1:delete");
        System.out.println("user1:update = " + user1Update);
        System.out.println("user1:delete = " + user1Delete);
        boolean user2Update = subject.isPermitted("user2:update");
        boolean user2Delete = subject.isPermitted("user2:delete");
        System.out.println("user2:update = " + user2Update);
        System.out.println("user2:delete = " + user2Delete);
        boolean user3Update = subject.isPermitted("user3:update");
        boolean user3Delete = subject.isPermitted("user3:delete");
        System.out.println("user3:update = " + user3Update);
        System.out.println("user3:delete = " + user3Delete);
    }

}
