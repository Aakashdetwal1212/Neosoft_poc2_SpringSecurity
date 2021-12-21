package com.poc2.exception;

import java.util.Date;

import lombok.Getter;

@Getter
public class ErrorDetails {

	private Date timeStamp;
	private String message;
	private String details;

	public ErrorDetails(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
}
