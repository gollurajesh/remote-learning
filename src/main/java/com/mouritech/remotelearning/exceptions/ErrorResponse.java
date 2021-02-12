package com.mouritech.remotelearning.exceptions;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	private String message;

	// Specific errors in API request processing
	private List<String> details;
}
