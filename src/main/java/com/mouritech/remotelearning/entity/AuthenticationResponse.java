package com.mouritech.remotelearning.entity;

import com.mouritech.remotelearning.model.LoginDTO;

import lombok.Data;

@Data
public class AuthenticationResponse extends LoginDTO {

	private final String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
}
