package com.user.dao;

import java.util.List;

import com.user.entity.User;
import com.user.entity.UserLoginModel;

public interface UserDao {

	public Long getUsersCount();

	public List<User> getUsers();
	
	public int addUser(User user);
	
	public User getUser(User user);

	public int deleteUser(Long userId);

	public int updateUser(User user);

	public User logUserIn(UserLoginModel userLoginModel);
}
