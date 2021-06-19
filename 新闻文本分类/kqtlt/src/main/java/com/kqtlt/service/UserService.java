package com.kqtlt.service;

import com.kqtlt.entity.User;

public interface UserService {
    Boolean insertOneUser(User user);
    User selectOneUserByNum(String num);
}
