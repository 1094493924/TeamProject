package com.kqtlt.mapper;

import com.kqtlt.entity.NewsFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository //告诉spring这是dao层
public interface NewsFileDao {
    //插入一条新闻
    boolean insertOneNews(NewsFile newsFile);
    //查询当前新闻个数
    int selectAllNewsCount();
    //查询一条新闻
    NewsFile selectOneNews(Integer id);
    //查询某一文件下的所有新闻
    List<NewsFile> selectAllNewByFileId(Integer id);
    //查询所有的文件
    List<NewsFile> selectAllNews();
    //选择所有正确的新闻
    int selectAllNewsRight();
    //选择所有错误的新闻
    int selectAllNewsWrong();
}
