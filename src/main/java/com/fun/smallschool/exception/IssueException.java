package com.fun.smallschool.exception;

import org.springframework.http.HttpStatus;

public class IssueException extends RuntimeException {

    private static final long serialVersionUID = -6593330219878485669L;

	private final HttpStatus status;
	private final String message;

	public IssueException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public IssueException(HttpStatus status, String message, Throwable exception) {
		super(exception);
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
