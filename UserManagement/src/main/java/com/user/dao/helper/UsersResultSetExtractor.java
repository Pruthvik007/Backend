package com.user.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.user.entity.User;

public class UsersResultSetExtractor implements ResultSetExtractor<List<User>>{

	@Override
	public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<User> users=new ArrayList<>();
		while(rs.next()) {
			User user = new User();
			user.setUserId(rs.getLong("userid"));
			user.setUserName(rs.getString("username"));
			user.setRole(rs.getString("role"));
			users.add(user);
		}
		return users;
	}
	
}
