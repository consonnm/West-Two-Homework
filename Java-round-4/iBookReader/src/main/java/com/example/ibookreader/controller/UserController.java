package com.example.ibookreader.controller;

import com.example.ibookreader.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value ="/user",produces = {"application/json;charset=UTF-8"})
public class UserController {
    @Autowired
    private IUserService iUserService;

    @ApiOperation("注册接口")
    @PostMapping("/insert")
    public String insertUser(@ApiParam("用户名")String userName,@ApiParam("密码")String passWord){
        return iUserService.insertUser(userName,passWord);
    }

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Boolean login(@ApiParam("用户名")String userName,@ApiParam("密码")String passWord){
        return iUserService.login( userName, passWord);
    }
    @ApiOperation("预操作接口爬取数据")
    @GetMapping("/aaaaa")
    public void login() throws MalformedURLException {
        iUserService.updategg();
    }

}
