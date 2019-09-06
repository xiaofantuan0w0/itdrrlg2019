package com.itdr.services.impl;

import com.itdr.common.ResponseCode;
import com.itdr.mappers.UserMapper;
import com.itdr.pojo.User;
import com.itdr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//专业技能，项目经历(名称，描述收货，责任描述，开发环境，硬件华宁)，个人技能（责任描述），校内经历，结尾自我评价
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    //登录
    @Override
    public ResponseCode selectOne(String username, String password) {
        if(username==null||username.equals("")){
            return ResponseCode.error(100,"用户名不能为空");
        }
        if(password==null||password.equals("")){
            return ResponseCode.error(100,"密码不能为空");
        }
       Integer pw = Integer.parseInt(password);
       User user =userMapper.selectOne(username,pw);
       return ResponseCode.success(user);
    }

    @Override
    //注册
    public int register(User user) {
        return userMapper.register(user);
    }

    @Override
    public User getOne(Integer id) {
        return userMapper.getOne(id);
    }

    //检查用户名是否有效检查用户名是否有效
    @Override
    public ResponseCode check_valid(String str, String type) {
       User user = userMapper.check_valid(str,type);
       if (user==null){
           if (type.equals("username")){
               return ResponseCode.error(1,"用户名已存在");
           }else {
               return ResponseCode.error(2,"邮箱已存在");
           }
       }
       return ResponseCode.success("校验成功");
    }

}
