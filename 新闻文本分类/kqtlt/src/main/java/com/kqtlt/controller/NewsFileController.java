package com.kqtlt.controller;

import com.kqtlt.common.R;
import com.kqtlt.entity.NewsFile;
import com.kqtlt.entity.Operation;
import com.kqtlt.entity.User;
import com.kqtlt.service.NewsFileService;
import com.kqtlt.service.OperationService;
import com.kqtlt.utils.Util;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NewsFileController {

    @Autowired
    private NewsFileService newsFileService;

    @Autowired
    private OperationService operationService;

    //单条新闻上传
    @RequestMapping("/single/news/upload")
    public R singleNewsUpload(@RequestBody String upload) throws JSONException, IOException {
        if (upload==null)
            return R.ok().data("reason","当前上传没有数据");

        //查询当前的记录个数
        int count = newsFileService.selectAllNewsCount();

        JSONObject json=new JSONObject(upload);

        //需要自定义--------------------
        File dir=new File("D:\\新闻文本分类");

        if (!dir.exists())
            dir.mkdirs();

        //向文件中写内容
        File file=new File(dir,"input.txt");
        File outPut=new File(dir,"output.txt");
        System.out.println("真实文件地址："+file.getAbsolutePath());
        System.out.println("真实类型地址："+outPut.getAbsolutePath());
        //向文件中写内容
        FileUtils.writeStringToFile(file,json.getString("news"),"UTF-8");

        //新闻数据存入数据库
        NewsFile newsFile=new NewsFile();
        newsFile.setNewsId(count+1);
        newsFile.setNewsContent(json.getString("news"));
        newsFile.setNewsCategoryRight(json.getString("type"));
        newsFile.setFileId(0);

        //计算当前项的正确类型,调用python处理
        String commandStr = "F:\\anaconda\\envs\\TF13\\python predict.py";
        Util.exeCmd(commandStr);

        //读取python调用的结果
        String realType=FileUtils.readFileToString(outPut, "UTF-8");
        System.out.println("text:"+realType);
        System.out.println("索引:"+realType.length()+","+realType.indexOf('\n')+","+realType.lastIndexOf('\n'));
        System.out.println("realType:"+realType.substring(0,realType.indexOf('\n')-1));

        newsFile.setNewsCategoryAnalysis(realType.substring(0,realType.indexOf('\n')-1));
        //------------------------------------------
        //计算准确率
        if (newsFile.getNewsCategoryRight().equals(newsFile.getNewsCategoryAnalysis()))
            newsFile.setNewsRate("100%");
        else
            newsFile.setNewsRate("0%");

        //删除文件
        file.delete();
        outPut.delete();

        Operation operation=new Operation();
        operation.setOperation("请求单条新闻上传数据库");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);

        //将所有的数据存入数据库中
        Boolean save = newsFileService.insertOneNews(newsFile);

        if (save)
            return R.ok().data("reason","一则新闻上传成功").count(count+1);
        return R.ok().data("reason","上传新闻失败");
    }

    //分析刚才上传的新闻
    @RequestMapping("/analysis/one/news/{id}")
    public R analysisOneNews(@PathVariable Integer id){
        NewsFile newsFile = newsFileService.selectOneNews(id);
        Map<String,String> map=new HashMap<>();
        map.put("newsCategoryAnalysis",newsFile.getNewsCategoryAnalysis());
        map.put("newsCategoryRight",newsFile.getNewsCategoryRight());
        map.put("newsRightRate",newsFile.getNewsRate());
        map.put("id","1");
        Operation operation=new Operation();
        operation.setOperation("请求分析单条新闻");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        return R.ok().count(1).data("data",map);
    }

    //测试数据传输格式
    @RequestMapping("/show/data")
    public R showData(){
        return R.ok().data("haha","hahaha").count(20);
    }

    //测试数据传输格式
    @RequestMapping("/show")
    public R show(){
        return R.ok().count(100);
    }
}
