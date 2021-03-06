package com.gamazing.springbootshirodemo.shiro;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /* 添加shiro的内置过滤器(实现权限相关的拦截)
            anon: 不需要认证可以直接访问
            authc: 必须认证才可以访问
            user: 如果使用rememberMe的功能才可以直接访问
            perms: 该资源必须得到资源权限才可以访问
            role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        /*filterMap.put("/add", "authc");
        filterMap.put("/update", "authc");*/
        filterMap.put("/testThymeleaf", "anon");
        filterMap.put("/login", "anon");

        //授权过滤器
        //filterMap.put("/add", "perms[user:add]");
        //filterMap.put("/update", "perms[user:update]");



        filterMap.put("/*", "authc");

        // 设置过滤权限
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 修改默认登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        // 设置为授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    /**
     * 创建Realm
     */
    @Bean("myRealm")
    public MyRealm getRealm() {
        return new MyRealm();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

}
