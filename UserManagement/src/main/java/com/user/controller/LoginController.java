package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.Response;
import com.user.entity.User;
import com.user.entity.UserLoginModel;
import com.user.exceptions.UserLoginException;
import com.user.service.UserService;
import com.user.utils.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/logUserIn")
	public Response logUserIn(UserLoginModel userLoginModel) {
		try {
			User user = userService.logUserIn(userLoginModel);
			if (Validator.isNotEmpty(user)) {
				return new Response(user, Response.Status.SUCCESS, null);
			} else {
				return new Response(null, Response.Status.FAILURE, "No User Found With Provided Credentials");
			}
		} catch (UserLoginException e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, "Unable To Login");
		}
	}
}
