package com.fun.smallschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fun.smallschool.payload.response.APIResponse;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private transient APIResponse APIResponse;

	private String resource;
	private String field;
	private Object fieldValue;

	public NotFoundException(String resource, String field, Object fieldValue) {
		super();
		this.resource = resource;
		this.field = field;
		this.fieldValue = fieldValue;
	}

    public String getFieldName() {
        return this.field;
    }

    public Object getFieldValue() {
        return this.fieldValue;
    }

	public String getResourceName() {
        return this.resource;
	}

	public APIResponse getAPIResponse() {
        return this.APIResponse;
	}

	private void setAPIResponse() {
		String message = String.format("%s COULD NOT FOUND WITH %s: '%s'", this.resource, this.field, fieldValue);
		APIResponse = new APIResponse(Boolean.FALSE, message);
	}
}
