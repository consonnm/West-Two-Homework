package com.example.hotSpot.shrio;

import com.example.hotSpot.entity.User;
import com.example.hotSpot.service.impl.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     * 只要访问加上授权的资源都会调用改方法
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //到数据库查询当前用户的授权的字符串
        Subject subject= SecurityUtils.getSubject();
        User user =(User) subject.getPrincipal();
        info.addRole(user.getRole());
        System.out.println("执行认证");
        System.out.println(user.getRole());
        return info;
    }
    /**
     * 执行认证逻辑
     * 只要使用subject.login(token) 就会调用该方法
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1、判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //如果传入的是用户名
        User user = userService.queryById(token.getUsername());
        if(user==null)
            return null;
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}

