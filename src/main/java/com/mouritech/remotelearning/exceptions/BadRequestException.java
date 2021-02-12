package com.mouritech.remotelearning.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
}
