package com.frog.kbo_community.domain.auth.usecase.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class RenewAccessTokenCommand {
	private final String refreshToken;
	private LocalDateTime cmdCreatedAt = LocalDateTime.now();

	@Builder
	public RenewAccessTokenCommand(String refreshToken, LocalDateTime cmdCreatedAt) {
		this.refreshToken = refreshToken;
		if (cmdCreatedAt != null) {
			this.cmdCreatedAt = cmdCreatedAt;
		}
	}
}
