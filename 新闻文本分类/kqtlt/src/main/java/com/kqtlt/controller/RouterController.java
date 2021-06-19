package com.kqtlt.controller;

import com.kqtlt.entity.Operation;
import com.kqtlt.entity.User;
import com.kqtlt.service.OperationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RouterController {

    @Autowired
    private OperationService operationService;

    //访问首页
    @RequestMapping({"/","/index"})
    public String index(){
        Operation operation=new Operation();
        operation.setOperation("请求首页");
        operation.setUserName("");
        operationService.insertOneOperation(operation);
        return "index";
    }

    //访问单条数据页面
    @RequestMapping("/single")
    public String single(){
        Operation operation=new Operation();
        operation.setOperation("请求单条新闻导入页面");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        return "single";
    }

    //访问多条数据导入
    @RequestMapping("/batch")
    public String batch(){
        Operation operation=new Operation();
        operation.setOperation("请求多条新闻导入页面");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        return "batch";
    }

    //访问统计信息
    @RequestMapping("/statistic")
    public String statistic(){
        Operation operation=new Operation();
        operation.setOperation("请求统计页面");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        return "statistic";
    }

    //用户登录
    @RequestMapping("/login")
    public String login(){
        Operation operation=new Operation();
        operation.setOperation("用户登录页面");
        operation.setUserName("");
        operationService.insertOneOperation(operation);
        return "login";
    }

    //用户登出
    @RequestMapping("/login/out")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        Operation operation=new Operation();
        operation.setOperation("请求登出");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        subject.logout();
        return "index";
    }

    //请求操作页面
    @RequestMapping("/operation")
    public String operation(){
        Operation operation=new Operation();
        operation.setOperation("请求统计操作记录页面");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        return "operation";
    }

}
