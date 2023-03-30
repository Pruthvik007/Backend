package com.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private Object data;
	private Status status;
	private String message;
	
	public enum Status{
		SUCCESS("SUCCESS"),FAILURE("FAILURE"),ERROR("ERROR");
		Status(String string) {
		}
	}
}
