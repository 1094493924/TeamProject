package com.kqtlt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kqtlt.entity.Operation;
import com.kqtlt.entity.User;
import com.kqtlt.service.OperationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private OperationService operationService;

    @RequestMapping("/require/login")
    public JSONObject requireLogin(@RequestBody String request){
        JSONObject json= JSON.parseObject(request);
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();//获取当前用户

        JSONObject response=new JSONObject();

        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(json.getString("id"),json.getString("password"));
        try {
            subject.login(token);
            response.put("code",0);
            response.put("data","登录成功");
            Operation operation=new Operation();
            operation.setOperation("请求登录");
            User user = (User) subject.getSession().getAttribute("loginUser");
            operation.setUserName(user.getUserName());
            operationService.insertOneOperation(operation);
            return response;
        }catch (Exception e){
            response.put("code",-1);
            response.put("data","登录失败");
            return response;
        }
    }
}
