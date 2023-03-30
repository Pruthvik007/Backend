package com.user.utils;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class UserDataSource {

	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/users");
		dataSource.setUsername("root");
		dataSource.setPassword("2512");
		return dataSource;
	}

	@Bean
	public NamedParameterJdbcTemplate getTemplate() {
		return new NamedParameterJdbcTemplate(mysqlDataSource());
	}

}
