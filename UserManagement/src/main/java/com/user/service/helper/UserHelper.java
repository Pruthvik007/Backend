package com.user.service.helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserHelper {
	public Long generateUserId(Long userId) {
		return userId == 0 ? 100l : userId + 1;
	}
}
