package com.user.service.helper;

import com.user.entity.User;
import com.user.entity.UserLoginModel;
import com.user.utils.Validator;

public class UserValidator {

	private UserValidator() {

	}

	public static String validateUser(User user) {
		String errorMessage = null;
		if (Validator.isEmpty(user)) {
			errorMessage = "Invalid User Details";
		} else if (Validator.isEmpty(user.getUserName())) {
			errorMessage = "User Name Cannot Be Empty";
		} else if (Validator.isEmpty(user.getPassword())) {
			errorMessage = "Password Cannot Be Empty";
		} else if (Validator.isEmpty(user.getRole())) {
			errorMessage = "User Role Cannot Be Empty";
		}
		return errorMessage;
	}

	public static String validateUserLoginModel(UserLoginModel userLoginModel) {
		String errorMessage = null;
		if (Validator.isEmpty(userLoginModel)) {
			errorMessage = "Invalid User Details";
		} else if (Validator.isEmpty(userLoginModel.getUserName())) {
			errorMessage = "User Name Cannot Be Empty";
		} else if (Validator.isEmpty(userLoginModel.getPassword())) {
			errorMessage = "Password Cannot Be Empty";
		}
		return errorMessage;
	}
}
