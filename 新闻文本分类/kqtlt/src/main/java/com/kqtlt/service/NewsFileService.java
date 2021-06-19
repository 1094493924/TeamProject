package com.kqtlt.service;

import com.kqtlt.entity.NewsFile;

import java.util.List;

public interface NewsFileService {
    Boolean insertOneNews(NewsFile newsFile);
    int selectAllNewsCount();
    NewsFile selectOneNews(Integer id);
    List<NewsFile> selectAllNewByFileId(Integer id);
    List<NewsFile> selectAllNews();
    int selectAllNewsRight();
    int selectAllNewsWrong();
}
