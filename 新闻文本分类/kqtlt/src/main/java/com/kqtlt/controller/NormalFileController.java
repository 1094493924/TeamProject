package com.kqtlt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kqtlt.entity.NewsFile;
import com.kqtlt.entity.NormalFile;
import com.kqtlt.entity.Operation;
import com.kqtlt.entity.User;
import com.kqtlt.service.NewsFileService;
import com.kqtlt.service.NormalFileService;
import com.kqtlt.service.OperationService;
import com.kqtlt.utils.Util;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class NormalFileController {

    @Autowired
    private NormalFileService normalFileService;

    @Autowired
    private NewsFileService newsFileService;

    @Autowired
    private OperationService operationService;

    @RequestMapping("/upload/news/file")
    public JSONObject uploadNewsFileType(@RequestParam("file") MultipartFile file) throws IOException {

        JSONObject json=new JSONObject();//返回数据
        //接收文件===上传的是新闻文件
        //-------需修改--------------
        File dir=new File("D:\\新闻文本分类");//指定文件目录

        if (!dir.exists())
            dir.mkdirs();

        File realFile=new File(dir.getPath()+"\\"+file.getOriginalFilename());
        System.out.println("真实文件路径："+realFile.getAbsolutePath());

        file.transferTo(realFile);

        //读取文件的内容
        String content = FileUtils.readFileToString(realFile, "UTF-8");

        //指定文件名
        //-------需修改--------------
        File rename = new File(dir.getPath() + "\\input.txt");
        realFile.renameTo(rename);
        realFile.delete();

        //查询当前文件的个数
        int fileCount = normalFileService.selectAllFileCount();

        //将文件存入数据库中
        NormalFile normalFile=new NormalFile();
        normalFile.setFileId(fileCount+1);
        normalFile.setFileName(file.getOriginalFilename());
        normalFile.setFileContent(content);
        normalFile.setFileSize(file.getSize());
        normalFile.setFileAddress(realFile.getAbsolutePath());
        normalFileService.insertOneNormalFile(normalFile);

        Operation operation=new Operation();
        operation.setOperation("请求上传文件存入数据库");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);

        json.put("code",0);
        json.put("count",fileCount+1);
        json.put("reason","文件存储成功");
        System.out.println("后台：fileCount:"+fileCount);

        return json;
    }


    @RequestMapping("/upload/news/file/type")
    public JSONObject uploadNewsFile(@RequestParam("file") MultipartFile file) throws IOException {

        JSONObject json=new JSONObject();

        //接收文件===上传的是文件的真实类型
        //-------需修改--------------
        File dir=new File("D:\\新闻文本分类");

        if (!dir.exists())
            dir.mkdirs();

        String filePath= dir.getPath()+"\\"+file.getOriginalFilename();

        File realTypeFile=new File(filePath);//储存真实结果的文件

        file.transferTo(realTypeFile);

        //分析文件，调用python代码
        //--------------调用python分析
        String commandStr = "F:\\anaconda\\envs\\TF13\\python predict.py";
        Util.exeCmd(commandStr);

        File inputFile=new File(dir+"\\input.txt");
        File outputFile=new File(dir+"\\output.txt");

        String newsFileContent = FileUtils.readFileToString(inputFile, "UTF-8");//读取新闻文件内容
        String forecastNews=FileUtils.readFileToString(outputFile,"UTF-8");//读取预测文件内容
        String realTypeNews=FileUtils.readFileToString(realTypeFile,"UTF-8");//读取真实类型文件内容

        // 将新闻存入数据库
        String[] newsContent = newsFileContent.split("\n");//将文件中所有的数据进行切割
        String[] newsRealType=realTypeNews.split("\n");//真实类型
        String[] newsType=forecastNews.split("\n");//预测类型
        int fileID = normalFileService.selectAllFileCount();
        int newsCount = newsFileService.selectAllNewsCount();
        for(int i=0;i<newsContent.length;i++){
            NewsFile newsFile=new NewsFile();
            newsFile.setNewsCategoryRight(newsRealType[i]);//真实类型
            newsFile.setNewsContent(newsContent[i]);//新闻内容
            newsFile.setNewsId(newsCount++);
            newsFile.setFileId(fileID);
            newsFile.setNewsCategoryAnalysis(newsType[i]);//分析类型
            if (newsFile.getNewsCategoryRight().equals(newsFile.getNewsCategoryAnalysis()))
                newsFile.setNewsRate("100%");
            else
                newsFile.setNewsRate("0%");
            newsFileService.insertOneNews(newsFile);//存入数据库
        }

        inputFile.delete();
        outputFile.delete();
        realTypeFile.delete();

        Operation operation=new Operation();
        operation.setOperation("请求上传文件信息");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);

        json.put("code",0);
        json.put("count",fileID);
        json.put("data","数据分析结束");
        //返回成功
        return json;
    }

    //查询某一文件对应的新闻信息
    @RequestMapping("/batch/news/upload/{id}")
    public JSONObject batchNewsUpload(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        //查询得到一批新闻
        List<NewsFile> newsFiles = newsFileService.selectAllNewByFileId(id);
        json.put("code",0);
        json.put("count",newsFiles.size());
        json.put("data", JSONArray.parseArray(JSON.toJSONString(newsFiles)));

        Operation operation=new Operation();
        operation.setOperation("请求查询多条新闻信息");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        operation.setUserName(user.getUserName());
        operationService.insertOneOperation(operation);
        return json;
    }
}
