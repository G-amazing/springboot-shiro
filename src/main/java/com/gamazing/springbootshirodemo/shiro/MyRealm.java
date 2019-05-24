package com.gamazing.springbootshirodemo.shiro;

import com.gamazing.springbootshirodemo.entity.Perm;
import com.gamazing.springbootshirodemo.entity.User;
import com.gamazing.springbootshirodemo.service.PermService;
import com.gamazing.springbootshirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private PermService permService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        // 根据用户id查询用户权限
        Perm perm = permService.findOneByUserId(user.getId());

        // 设置用户权限
        info.addStringPermission(perm.getPermission());

        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证");

        // 从参数中获取用户名密码并判断是否正确
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.findOneByUsername(token.getUsername());

        if (user == null) {
            // 如果失败返回null，shiro底层会抛出 UnKnowAccountException异常
            return null;
        }

        //
        // 第一个参数传递的是登录以后返回给shiro的信息，这个信息可以在授权的时候通过 SecurityUtils.getSubject().getPrincipal() 获取
        // 所以第一个参数一般传递用户的信息
        // 传递的第二个参数是从数据库中查出来的密码，shiro会自己验证
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
