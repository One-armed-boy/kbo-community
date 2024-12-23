package com.frog.kbo_community.controller.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class RenewAccessTokenResponse {
	private final String accessToken;

	@Builder
	public RenewAccessTokenResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
