package com.demo.shiro.spring.shiro;

import com.demo.shiro.spring.pojo.User;
import com.demo.shiro.spring.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author yaowk
 * @Date 2017/1/13 16:17
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("-------开始授权------");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //角色
        authorizationInfo.addRole("role1");
        //权限
        authorizationInfo.addStringPermission("user1:*");//字符串通配符权限
        authorizationInfo.addStringPermission("user2:view");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("-------- 认证开始 -----------");
        String userName = (String)token.getPrincipal(); //得到用户名
        User user = userService.getUserByName(userName);
        if(user != null){
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), super.getName());
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute("currentUser", user);
            return authcInfo;
        }else{
            return null;
        }
    }
}
