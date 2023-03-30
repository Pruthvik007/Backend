package com.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.user.dao.UserDao;
import com.user.dao.UserJpaRepository;
import com.user.entity.User;
import com.user.entity.UserLoginModel;
import com.user.exceptions.UserException;
import com.user.exceptions.UserLoginException;
import com.user.service.helper.UserHelper;
import com.user.service.helper.UserValidator;
import com.user.utils.Validator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public User addUser(User user) throws UserException {
		String errorMessage = UserValidator.validateUser(user);
		if (Validator.isNotEmpty(errorMessage)) {
			throw new UserException(errorMessage);
		}
		user.setUserId(UserHelper.generateUserId(userDao.getUsersCount()));
		return userDao.addUser(user) != 0 ? user : null;
	}

	@Override
	public User getUser(User user) {
		return userDao.getUser(user);
	}

	@Override
	public List<User> getUsers(int pageNumber, int numberOfRows, String sortBy) {
		PageRequest page = PageRequest.of(pageNumber, numberOfRows);
		if (Validator.isNotEmpty(sortBy)) {
			page = PageRequest.of(pageNumber, numberOfRows, Sort.by(sortBy));
		}
		return userJpaRepository.findAll(page).getContent();
	}

	@Override
	public int deleteUser(Long userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public int updateUser(User user) throws UserException {
		String errorMessage = UserValidator.validateUser(user);
		if (Validator.isNotEmpty(errorMessage)) {
			throw new UserException(errorMessage);
		}
		return userDao.updateUser(user);
	}

	@Override
	public User logUserIn(UserLoginModel userLoginModel) throws UserLoginException {
		String errorMessage = UserValidator.validateUserLoginModel(userLoginModel);
		if (Validator.isNotEmpty(errorMessage)) {
			throw new UserLoginException(errorMessage);
		}
		return userDao.logUserIn(userLoginModel);
	}

}
