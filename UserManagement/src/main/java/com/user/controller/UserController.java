package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.Response;
import com.user.entity.User;
import com.user.exceptions.UserException;
import com.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public Response addUser(@RequestBody User user) {
		try {
			user = userService.addUser(user);
			return new Response(user, Response.Status.SUCCESS, "User Added Succesfully");
		} catch (UserException e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, "Failed to Add User");
		}
	}

	@GetMapping("/getAllUsers")
	public Response getAllUsers() {
		try {
			return new Response(userService.getUsers(), Response.Status.SUCCESS, null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, "Unable To Get Users Data");
		}
	}

	@GetMapping("/getUsers")
	public Response getUsers(@RequestParam int pageNumber, @RequestParam int numberOfRows,
			@RequestParam(required = false) String sortBy) {
		try {
			return new Response(userService.getUsers(pageNumber, numberOfRows, sortBy), Response.Status.SUCCESS, null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, "Unable To Get Users Data");
		}
	}

	@DeleteMapping("/deleteUser/{userId}")
	public Response deleteUser(@PathVariable("userId") Long userId) {
		try {
			if (userService.deleteUser(userId) != 0) {
				return new Response(null, Response.Status.SUCCESS, "User Deleted Successfully");
			}
			return new Response(null, Response.Status.FAILURE, "No User Found!");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, "Unable To Delete");
		}
	}

	@PutMapping("/updateUser")
	public Response updateUser(@RequestBody User user) {
		try {
			if (userService.updateUser(user) != 0) {
				return new Response(null, Response.Status.SUCCESS, "User Updated Successfully");
			}
			return new Response(null, Response.Status.FAILURE, "No User Found!");
		} catch (UserException e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(null, Response.Status.FAILURE, "Unable To Update User");
		}
	}
}
