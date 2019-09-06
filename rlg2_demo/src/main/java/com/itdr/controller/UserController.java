package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.User;

import com.itdr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/user/")
public  class  UserController {

    static ResponseCode responseCode= new ResponseCode();
   @Autowired
   private UserService userService;

    @RequestMapping("login.do")  //登录
    @ResponseBody
    public ResponseCode selectOne(String username, String password,HttpSession session){
        responseCode = userService.selectOne(username,password);
        if (responseCode.getData()!=null){
            session.setAttribute("user",responseCode.getData());
        }
        return responseCode;
    }

    @RequestMapping("register.do") //注册
    @ResponseBody
    public void register(User user, HttpServletResponse response){
        int i = userService.register(user);
        try {
            response.getWriter().write(i+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("check_valid.do")//检查用户名是否有效
    @ResponseBody
    public ResponseCode check_valid(String str,String type){
        return userService.check_valid(str,type);
    }

    @RequestMapping("get_user_info.do")//获取登录用户信息
    @ResponseBody
    public ResponseCode get_user_info(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user==null){
            return ResponseCode.error(1,"用户未登录，无法获取当前用户信息");
        }
        return ResponseCode.success(user);
    }

    @RequestMapping("ceshi.do/{id}") //测试用
    @ResponseBody
    public String test(@PathVariable("id") Integer id){
        User user = userService.getOne(id);
//        System.out.println(user);
//        try {
//            response.getWriter().write(user.getUsername());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return user.toString();
    }
}
