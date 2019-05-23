package com.gamazing.springbootshirodemo.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = "admin";
        String password = "123456";

        // 从参数中获取用户名密码并判断是否正确
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (!token.getUsername().equals(username)) {
            // 如果失败返回null，shiro底层会抛出 UnKnowAccountException异常
            return null;
        }

        // 第二个参数是从数据库中查出来的密码，shiro会自己验证
        return new SimpleAuthenticationInfo("",password,"");
    }
}
