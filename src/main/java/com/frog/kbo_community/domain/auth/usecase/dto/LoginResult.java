package com.frog.kbo_community.domain.auth.usecase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResult {
	private final String accessToken;
	private final String refreshToken;

	@Builder
	public LoginResult(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
