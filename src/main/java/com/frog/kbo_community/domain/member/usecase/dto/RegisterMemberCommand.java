package com.frog.kbo_community.domain.member.usecase.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class RegisterMemberCommand {
	private final String email;
	private final String password;
	private LocalDateTime cmdCreatedAt = LocalDateTime.now();

	@Builder
	public RegisterMemberCommand(String email, String password, LocalDateTime cmdCreatedAt) {
		this.email = email;
		this.password = password;
		if (cmdCreatedAt != null) {
			this.cmdCreatedAt = cmdCreatedAt;
		}
	}
}
