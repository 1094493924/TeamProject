package com.kqtlt.service.impl;

import com.kqtlt.entity.NormalFile;
import com.kqtlt.mapper.NormalFileDao;
import com.kqtlt.service.NormalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormalFileServiceImpl implements NormalFileService {
    @Autowired
    private NormalFileDao normalFileDao;

    @Override
    public int selectAllFileCount() {
        return normalFileDao.selectAllFileCount();
    }

    @Override
    public Boolean insertOneNormalFile(NormalFile normalFile) {
        return normalFileDao.insertOneNormalFile(normalFile);
    }
}
