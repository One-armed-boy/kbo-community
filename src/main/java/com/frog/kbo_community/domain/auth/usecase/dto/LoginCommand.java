package com.frog.kbo_community.domain.auth.usecase.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class LoginCommand {
	private final String email;
	private final String password;
	private LocalDateTime cmdCreatedAt = LocalDateTime.now();

	@Builder()
	public LoginCommand(String email, String password, LocalDateTime cmdCreatedAt) {
		this.email = email;
		this.password = password;
		if (cmdCreatedAt != null) {
			this.cmdCreatedAt = cmdCreatedAt;
		}
	}
}
