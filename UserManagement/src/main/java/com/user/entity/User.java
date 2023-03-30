package com.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "userid")
	private Long userId;

	@Column(name = "username")
	private String userName;

	@Column(name="password")
	private String password;

	@Column(name="role")
	private String role;
	
	public static User copyUser(User user) {
		return new User(user.getUserId(), user.getUserName(), user.getPassword(), user.getRole());
	}
	
	public enum Status {
		CREATED("C"),
		ACTIVE("A"),
		INACTIVE("I");

		private Status(String string) {
			//To Prevent Creating Additional Statuses
		}
		
		private Status() {	
		}
	}
	
}
