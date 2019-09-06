package com.itdr.mappers;

import com.itdr.pojo.User;


public interface UserMapper {
    User selectOne(String username, Integer password);

    int register(User user);
    User getOne(Integer id);

    User check_valid(String str, String type);
}