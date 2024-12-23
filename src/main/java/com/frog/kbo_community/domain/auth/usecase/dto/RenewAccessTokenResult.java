package com.frog.kbo_community.domain.auth.usecase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class RenewAccessTokenResult {
	private String accessToken;

	@Builder
	public RenewAccessTokenResult(String accessToken) {
		this.accessToken = accessToken;
	}
}
