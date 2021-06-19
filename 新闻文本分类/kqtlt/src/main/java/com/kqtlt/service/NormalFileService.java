package com.kqtlt.service;

import com.kqtlt.entity.NormalFile;

public interface NormalFileService {
    int selectAllFileCount();
    Boolean insertOneNormalFile(NormalFile normalFile);
}
