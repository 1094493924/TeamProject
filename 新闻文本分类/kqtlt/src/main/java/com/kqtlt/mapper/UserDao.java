package com.kqtlt.mapper;

import com.kqtlt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository //告诉spring这是dao层
public interface UserDao {
    //插入一个用户
    Boolean insertOneUser(User user);
    //查询一个用户
    User selectOneUserByNum(String num);
}
