package com.kqtlt.mapper;

import com.kqtlt.entity.NormalFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository //告诉spring这是dao层
public interface NormalFileDao {

    //查询当前文件的个数
    int selectAllFileCount();
    //向数据库中存储一个文件
    Boolean insertOneNormalFile(NormalFile normalFile);

}
