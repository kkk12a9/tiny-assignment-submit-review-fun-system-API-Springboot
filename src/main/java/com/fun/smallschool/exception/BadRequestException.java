package com.fun.smallschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fun.smallschool.payload.response.APIResponse;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private APIResponse apiResponse;

	public BadRequestException(APIResponse apiResponse) {
		super();
		this.apiResponse = apiResponse;
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public APIResponse getApiResponse() {
		return apiResponse;
	}
}

