package com.kqtlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.kqtlt.entity.Operation;
import com.kqtlt.entity.User;
import com.kqtlt.service.OperationService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    //查询所有用户的操作
    @RequestMapping("/operation/all")
    public JSONObject operationAll(){
        JSONObject json=new JSONObject();

        List<Operation> operations = operationService.selectAllOperation();
        json.put("code",0);
        json.put("count",operations.size());
        json.put("data",operations);
        Operation operation=new Operation();
        operation.setOperation("请求查询所有用户操作的信息");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        return json;
    }


}
