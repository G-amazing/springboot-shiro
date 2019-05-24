package com.gamazing.springbootshirodemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {



    @RequestMapping("/toLogin")
    public String hello() {
        return "login";
    }

    @RequestMapping("/unAuth")
    public String unAuth() {
        return "unAuth";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("testThymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("name","张三");
        return "test";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        // -------使用shiro编写认证操作-------
        // 1、获取 Subject
        Subject subject = SecurityUtils.getSubject();

        // 2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 3、执行登录方法
        try {
            // 没有错误证明登录成功
            subject.login(token);
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
            // 登录失败：用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            // 登录失败：密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
