package com.user.dao.helper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.user.entity.User;
import com.user.entity.UserLoginModel;
import com.user.utils.Validator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDaoHelper {
	public MapSqlParameterSource getUserParams(User user) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("userIdFlag", getFlagValue(user.getUserId()));
		mapSqlParameterSource.addValue("userid", user.getUserId());
		mapSqlParameterSource.addValue("userNameFlag", getFlagValue(user.getUserName()));
		mapSqlParameterSource.addValue("username", user.getUserName());
		mapSqlParameterSource.addValue("roleFlag", getFlagValue(user.getRole()));
		mapSqlParameterSource.addValue("role", user.getRole());
		mapSqlParameterSource.addValue("passwordFlag", getFlagValue(user.getPassword()));
		mapSqlParameterSource.addValue("password", user.getPassword());
		return mapSqlParameterSource;
	}

	private int getFlagValue(Object param) {
		return Validator.isEmpty(param) ? 0 : 1;
	}

	public static SqlParameterSource getUserParams(UserLoginModel userLoginModel) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("userIdFlag", getFlagValue(userLoginModel.getUserId()));
		mapSqlParameterSource.addValue("userid", userLoginModel.getUserId());
		mapSqlParameterSource.addValue("userNameFlag", getFlagValue(userLoginModel.getUserName()));
		mapSqlParameterSource.addValue("username", userLoginModel.getUserName());
		mapSqlParameterSource.addValue("passwordFlag", getFlagValue(userLoginModel.getPassword()));
		mapSqlParameterSource.addValue("password", userLoginModel.getPassword());
		return mapSqlParameterSource;
	}
}
