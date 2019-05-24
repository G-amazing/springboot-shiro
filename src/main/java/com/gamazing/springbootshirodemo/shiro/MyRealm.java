package com.gamazing.springbootshirodemo.shiro;

import com.gamazing.springbootshirodemo.entity.User;
import com.gamazing.springbootshirodemo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

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

        // 从参数中获取用户名密码并判断是否正确
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(token.getUsername());

        User user = userService.findOneByUsername(token.getUsername());

        if (user == null) {
            // 如果失败返回null，shiro底层会抛出 UnKnowAccountException异常
            return null;
        }

        // 传递的第二个参数是从数据库中查出来的密码，shiro会自己验证
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
