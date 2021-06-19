package com.kqtlt.service.impl;

import com.kqtlt.entity.User;
import com.kqtlt.mapper.UserDao;
import com.kqtlt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;
    @Override
    public Boolean insertOneUser(User user) {
        return userDao.insertOneUser(user);
    }

    @Override
    public User selectOneUserByNum(String num) {
        return userDao.selectOneUserByNum(num);
    }
}
