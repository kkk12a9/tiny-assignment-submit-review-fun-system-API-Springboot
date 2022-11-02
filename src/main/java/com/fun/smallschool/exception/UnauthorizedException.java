package com.fun.smallschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fun.smallschool.payload.response.APIResponse;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private APIResponse APIResponse;
	private String message;

	public UnauthorizedException(APIResponse apiResponse) {
		super();
		APIResponse = APIResponse;
	}

	public UnauthorizedException(String message) {
		super(message);
		this.message = message;
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public APIResponse getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(APIResponse apiResponse) {
		this.apiResponse = apiResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
