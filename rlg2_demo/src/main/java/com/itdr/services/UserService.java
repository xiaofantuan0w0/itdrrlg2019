package com.itdr.services;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.User;

public interface UserService {
    ResponseCode selectOne(String username, String password);
    int register(User user);

    User getOne(Integer id);

    ResponseCode check_valid(String str, String type);


}
