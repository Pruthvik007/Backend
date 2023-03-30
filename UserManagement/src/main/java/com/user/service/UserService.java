package com.user.service;

import java.util.List;

import com.user.entity.User;
import com.user.entity.UserLoginModel;
import com.user.exceptions.UserException;
import com.user.exceptions.UserLoginException;

public interface UserService {
	
	public List<User> getUsers();
	
	public User addUser(User user) throws UserException;
	
	public User getUser(User user);

	public List<User> getUsers(int pageNumber, int numberOfRows, String sortBy);

	public int deleteUser(Long userId);

	public int updateUser(User user) throws UserException;

	public User logUserIn(UserLoginModel userLoginModel) throws UserLoginException;

}
