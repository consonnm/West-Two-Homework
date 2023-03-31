package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IUserDao;
import com.example.hotSpot.entity.User;
import com.example.hotSpot.service.IUserService;
import com.example.hotSpot.utils.AliyunOSSUtil;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class UserService extends ServiceImpl<IUserDao, User> implements IUserService {
	@Autowired
	AliyunOSSUtil aliyunOSSUtil;
	@Override
	public User queryById(String userId) {
		return getOne(new LambdaQueryWrapper<User>()
				.eq(User::getUsername,userId)
		);
	}
	@Override
	public Boolean insertUser(String username, String password) {
		User user = new User();
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		Md5Hash MD5 = new Md5Hash(password,salt,2);
		user.setPassword(MD5.toHex());
		user.setUsername(username);
		user.setSalt(salt);
		user.setRole("user::user");
		return save(user);
	}
	@Override
	public String changeUser(int userId, String password1,String password2,String password3) {
		User user = baseMapper.selectById(userId);
		String password = new SimpleHash("md5", password1, user.getSalt(), 2).toString();
		System.out.println(password);
		System.out.println(user.getPassword());
		if(!Objects.equals(password2, password3))
				return "两次密码输入不一致";
		else if(password.equals(user.getPassword())) {
			Md5Hash MD5 = new Md5Hash(password2, user.getSalt(), 2);
			user.setPassword(MD5.toHex());
			saveOrUpdate(user);
			return "修改成功";
		}
		return "原密码错误";
	}
	@Override
	public Boolean updateUer(int userId,String nickname,String phone,String age,String qq){
		User user = baseMapper.selectById(userId);
		user.setUserId(userId);
		user.setAge(age);
		user.setNickname(nickname);
		user.setQq(qq);
		user.setPhone(phone);
		return saveOrUpdate(user);
	}

	@Override
	public Boolean updateImage(MultipartFile file, int userId) {
		String url = aliyunOSSUtil.upload(file);
		User user = baseMapper.selectById(userId);
		user.setImage(url);
		user.setUserId(userId);
		return saveOrUpdate(user);
	}
	@Override
	public IPage<User> findByPage(Page<User> page, LambdaQueryWrapper<User> userLambdaQueryWrapper){
		return  baseMapper.selectPage(page,userLambdaQueryWrapper);
	}
}
