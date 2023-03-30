package com.user.dao.queries;

public class UserDaoQueries {
	public static final String QUERY_GET_USERS = "select userid, username, role from user";
	public static final String QUERY_ADD_USER = "insert into user(userid,username,role,password) values(:userid,:username,:role,:password)";
	public static final String QUERY_GET_USERS_COUNT = "select max(user.userid) from user";
	public static final String QUERY_GET_USER = "select userid, username, role from user where (:userIdFlag=0 OR userid=:userid) and (:userNameFlag=0 OR username=:username) and(:roleFlag=0 OR role=:role) and(:passwordFlag=0 OR password=:password)";
	public static final String QUERY_DELETE_USER = "delete from user where userid=:userid";
	public static final String QUERY_UPDATE_USER = "UPDATE user SET username=:username, role=:role, password=:password WHERE userid=:userid;";
	public static final String QUERY_LOG_USER_IN = "select userid, username, role from user where (:userIdFlag=0 OR userid=:userid) and (:userNameFlag=0 OR username=:username) and(:passwordFlag=0 OR password=:password)"; 
}
