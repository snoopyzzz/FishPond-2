package com.zz.controller;

import com.zz.entity.User;
import com.zz.service.UserService;
import com.zz.util.Jo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/8
 * Time: 16:54
 * Description: No Description
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    protected UserService getService(){
        return userService;
    }

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

//    @ResponseBody
//    @RequestMapping(value = "login", method = {RequestMethod.POST})
//    public Jo login(User user){
//        return this.getService().login(user);
//    }

    @ResponseBody
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public Jo login(User user){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            return new Jo().fail(e.getMessage());
        }
        return this.getService().login(user);
    }
}
