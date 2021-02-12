package com.mouritech.remotelearning.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String exception) {
		super(exception);
	}
}
