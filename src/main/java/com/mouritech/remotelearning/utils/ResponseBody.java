package com.mouritech.remotelearning.utils;

import lombok.Data;

@Data
public class ResponseBody {

	private int status;

	private String message;

	public ResponseBody(int status, String message) {
		super();
		this.status = status;
		if (status == 500) {
			this.message = "We are unable process your request. Please try later.";
		} else {
			this.message = message;
		}
	}
}
