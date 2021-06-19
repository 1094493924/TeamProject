package com.kqtlt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kqtlt.entity.NewsFile;
import com.kqtlt.entity.Operation;
import com.kqtlt.entity.User;
import com.kqtlt.service.NewsFileService;
import com.kqtlt.service.OperationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {

    @Autowired
    private NewsFileService newsFileService;

    @Autowired
    private OperationService operationService;

    //统计所有新闻数据的分析情况
    @RequestMapping("/statistic/news")
    public JSONObject statisticNews(){
        JSONObject json=new JSONObject();
        List<NewsFile> newsFiles = newsFileService.selectAllNews();
        json.put("code",0);
        json.put("count",newsFiles.size());
        json.put("data", JSONArray.parseArray(JSON.toJSONString(newsFiles)));
        json.put("right",newsFileService.selectAllNewsRight());
        json.put("wrong",newsFileService.selectAllNewsWrong());
        Operation operation=new Operation();
        operation.setOperation("统计所有新闻正确性");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        return json;
    }

}
