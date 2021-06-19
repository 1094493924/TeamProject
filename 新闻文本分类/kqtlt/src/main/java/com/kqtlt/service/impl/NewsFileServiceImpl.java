package com.kqtlt.service.impl;

import com.kqtlt.entity.NewsFile;
import com.kqtlt.mapper.NewsFileDao;
import com.kqtlt.service.NewsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsFileServiceImpl implements NewsFileService {

    @Autowired
    private NewsFileDao newsFileDao;

    @Override
    public Boolean insertOneNews(NewsFile newsFile) {
        return newsFileDao.insertOneNews(newsFile);
    }

    @Override
    public int selectAllNewsCount() {
        return newsFileDao.selectAllNewsCount();
    }

    @Override
    public NewsFile selectOneNews(Integer id) {
        return newsFileDao.selectOneNews(id);
    }

    @Override
    public List<NewsFile> selectAllNewByFileId(Integer id) {
        return newsFileDao.selectAllNewByFileId(id);
    }

    @Override
    public List<NewsFile> selectAllNews() {
        return newsFileDao.selectAllNews();
    }

    @Override
    public int selectAllNewsRight() {
        return newsFileDao.selectAllNewsRight();
    }

    @Override
    public int selectAllNewsWrong() {
        return newsFileDao.selectAllNewsWrong();
    }
}
