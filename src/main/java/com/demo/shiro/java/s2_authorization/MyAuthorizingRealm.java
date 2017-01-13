package com.demo.shiro.java.s2_authorization;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author yaowk
 * @Date 2017/1/13 13:48
 */
public class MyAuthorizingRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //角色
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
        //权限
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("user2:*");//字符串通配符权限
        authorizationInfo.addStringPermission("user3:update");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal(); //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"yao".equals(username)) {
            System.out.println(" ------------- 用户名错误" );
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            System.out.println(" ------------- 密码错误" );
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
        System.out.println(" ------------- 认证成功" );
        return new SimpleAuthenticationInfo(username, password, getName());
    }


}
