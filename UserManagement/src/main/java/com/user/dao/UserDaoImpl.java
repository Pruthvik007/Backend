package com.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.user.dao.helper.UserDaoHelper;
import com.user.dao.helper.UserRowMapper;
import com.user.dao.queries.UserDaoQueries;
import com.user.entity.User;
import com.user.entity.UserLoginModel;
import com.user.utils.Validator;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<User> getUsers() {
		try {
			return namedParameterJdbcTemplate.query(UserDaoQueries.QUERY_GET_USERS, new UserRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int addUser(User user) {
		return namedParameterJdbcTemplate.update(UserDaoQueries.QUERY_ADD_USER, UserDaoHelper.getUserParams(user));
	}

	@Override
	public Long getUsersCount() {
		return namedParameterJdbcTemplate.query(UserDaoQueries.QUERY_GET_USERS_COUNT, new ResultSetExtractor<Long>() {

			@Override
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getLong(1);
				}
				return 0l;
			}
		});
	}

	@Override
	public User getUser(User user) {
		User user1 = User.copyUser(user);
		user1.setPassword(null);
		List<User> users = namedParameterJdbcTemplate.query(UserDaoQueries.QUERY_GET_USER,
				UserDaoHelper.getUserParams(user1), new UserRowMapper());
		return Validator.isEmpty(users) ? null : users.get(0);
	}

	@Override
	public int deleteUser(Long userId) {
		return namedParameterJdbcTemplate.update(UserDaoQueries.QUERY_DELETE_USER,
				UserDaoHelper.getUserParams(User.builder().userId(userId).build()));
	}

	@Override
	public int updateUser(User user) {
		return namedParameterJdbcTemplate.update(UserDaoQueries.QUERY_UPDATE_USER, UserDaoHelper.getUserParams(user));
	}

	@Override
	public User logUserIn(UserLoginModel userLoginModel) {
		List<User> users = namedParameterJdbcTemplate.query(UserDaoQueries.QUERY_LOG_USER_IN,
				UserDaoHelper.getUserParams(userLoginModel), new UserRowMapper());
		return Validator.isEmpty(users) ? null : users.get(0);
	}
}
