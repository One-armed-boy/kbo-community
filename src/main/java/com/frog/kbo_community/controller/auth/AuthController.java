package com.frog.kbo_community.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frog.kbo_community.controller.auth.dto.LoginRequest;
import com.frog.kbo_community.controller.auth.dto.LoginResponse;
import com.frog.kbo_community.domain.auth.usecase.LoginUsecase;
import com.frog.kbo_community.domain.auth.usecase.dto.LoginCommand;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
	private final LoginUsecase loginUsecase;

	@Autowired
	public AuthController(LoginUsecase loginUsecase) {
		this.loginUsecase = loginUsecase;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		var result = this.loginUsecase.execute(
			LoginCommand.builder()
				.email(request.getEmail())
				.password(request.getPassword())
				.build()
		);

		log.info("로그인 성공 (email: {})", "");

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				LoginResponse.builder()
					.accessToken(result.getAccessToken())
					.refreshToken(result.getRefreshToken())
					.build()
			);
	}
}
