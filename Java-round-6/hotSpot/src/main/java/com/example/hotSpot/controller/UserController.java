package com.example.hotSpot.controller;


import com.example.hotSpot.entity.User;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IUserService;
import com.example.hotSpot.utils.RsaUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    RsaUtil rsaUtil;
    @ApiOperation("登录接口")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名"),
            @ApiImplicitParam(name="password",value="密码"),
    })
    public ResultVo login(String username,String password) {
        log.info("登录接口");
        //加密传输的解密
        //password=rsaUtil.decryptByPrivateKey(password,rsaUtil.keyPairMap.get(rsaUtil.PRIVATE_KEY_NAME));
        Subject subject = SecurityUtils.getSubject();
        User us = iUserService.queryById(username);
        if (us != null) {
            String salt = us.getSalt();
            password = new SimpleHash("md5", password, salt, 2).toString();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            log.info("认证状态："+subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return new ResultVo().setMessage("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return new ResultVo().setMessage("密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("user", us);
        map.put("token", token);
        return new ResultVo().setCode(200).setMessage("登录成功").setData(map);
    }
    @ApiOperation("登陆状态查询接口")
    @GetMapping("/status")
    public ResultVo status() {
        log.info("登陆状态查询接口");
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            User user =(User) subject.getPrincipal();
            return new ResultVo().setCode(200).setData(user);
        }
        else
            return new ResultVo().setCode(401).setMessage("用户名未登陆");
    }
    @ApiOperation("注册接口")
    @PostMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名"),
            @ApiImplicitParam(name="password",value="密码"),
    })
    public ResultVo register(String username,String password) {
        log.info("注册接口");
        //password=rsaUtil.decryptByPrivateKey(password,RsaUtil.keyPairMap.get(rsaUtil.PRIVATE_KEY_NAME));
        if (username == null || username.equals("")) {
            return new ResultVo().setCode(401).setMessage("用户名不能为空");
        } else if (password == null || password.equals("")) {
            return new ResultVo().setCode(401).setMessage("密码不能为空");
        } else if (iUserService.queryById(username) != null) {
            return new ResultVo().setCode(401).setMessage("用户名已被占用");
        } else {
            return new ResultVo().setCode(200).setData(iUserService.insertUser(username, password));
        }
    }
    @ApiOperation("修改密码接口")
    @PostMapping("/change")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id"),
            @ApiImplicitParam(name="password1",value="原来的密码"),
            @ApiImplicitParam(name="password2",value="修改的密码"),
            @ApiImplicitParam(name="password3",value="重复修改的密码"),
    })
    public ResultVo change(int userId,String password1,String password2,String password3) {
        log.info("修改密码");
        //password1=rsaUtil.decryptByPrivateKey(password1,rsaUtil.keyPairMap.get(rsaUtil.PRIVATE_KEY_NAME));
        //password2=rsaUtil.decryptByPrivateKey(password2,rsaUtil.keyPairMap.get(rsaUtil.PRIVATE_KEY_NAME));
        //password3=rsaUtil.decryptByPrivateKey(password3,rsaUtil.keyPairMap.get(rsaUtil.PRIVATE_KEY_NAME));
        return new ResultVo().setCode(200).setData(iUserService.changeUser(userId, password1,password2,password3));
    }
    @ApiOperation("登出接口")
    @GetMapping("/logout")
    public ResultVo logout() {
        log.info("登出接口");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResultVo().setCode(200).setMessage("退出成功");
    }
    @ApiOperation("用户信息查询接口")
    @GetMapping("/select")
    @ApiImplicitParam(name = "userId",value = "用户Id")
    public ResultVo select(String userId) {
        log.info("用户信息查询");
        User us = iUserService.getById(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("user", us);
        return new ResultVo().setCode(401).setMessage("登录成功").setData(map);
    }
    @ApiOperation("用户基础信息修改接口")
    @PutMapping("/baseMessageUpdate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id"),
            @ApiImplicitParam(name="nickname",value="昵称"),
            @ApiImplicitParam(name="phone",value="电话"),
            @ApiImplicitParam(name="age",value="年龄"),
            @ApiImplicitParam(name="qq",value="qq"),
            @ApiImplicitParam(name="email",value="邮箱"),
    })
    public ResultVo baseUpdate(int userId,String nickname,String phone,String age,String qq) {
        log.info("用户基础信息修改接口");
        return new ResultVo().setCode(200).setData(iUserService.updateUer(userId,nickname, phone, age, qq));
    }

    @ApiOperation("用户图片修改接口")
    @PostMapping("/photoUpdate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id"),
            @ApiImplicitParam(name="file",value="照片"),
    })
    public ResultVo photoUpdate(MultipartFile file,int userId) {
        log.info("用户图片修改接口");
        return new ResultVo().setCode(200).setData(iUserService.updateImage(file, userId));
    }
    @ApiOperation("生成公匙私匙")
    @PostMapping("/creatPublicKey")
    public ResultVo creatPublicKey() {
        log.info("生成公匙私匙");
        rsaUtil.createRSAKeys();
        return new ResultVo().setCode(200).setData(true);
    }
    @ApiOperation("获取公匙")
    @PostMapping("/getPublicKey")
    public ResultVo getPublicKey() {
        log.info("获取公匙");
        return new ResultVo().setCode(200).setData(RsaUtil.keyPairMap.get(RsaUtil.PUBLIC_KEY_NAME));
    }
    @ApiOperation("获取图片接口")
    @PostMapping("/photo")
    public ResultVo getPhoto(String username) {
        return new ResultVo().setCode(200).setData(iUserService.queryById(username).getImage());
    }
}