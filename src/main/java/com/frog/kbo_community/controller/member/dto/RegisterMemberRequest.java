package com.frog.kbo_community.controller.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class RegisterMemberRequest {
	@Email(message = "올바른 이메일이 아닙니다.")
	@NotBlank
	private final String email;

	@NotBlank
	private final String password;

	@Builder
	public RegisterMemberRequest(String email , String password) {
		this.email = email;
		this.password = password;
	}
}