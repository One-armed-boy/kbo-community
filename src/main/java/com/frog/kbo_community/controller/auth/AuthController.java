package com.frog.kbo_community.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frog.kbo_community.auth.jwt.JwtService;
import com.frog.kbo_community.controller.auth.dto.LoginRequest;
import com.frog.kbo_community.controller.auth.dto.LoginResponse;
import com.frog.kbo_community.controller.auth.dto.RenewAccessTokenResponse;
import com.frog.kbo_community.domain.auth.constant.JwtMetadata;
import com.frog.kbo_community.domain.auth.usecase.LoginUsecase;
import com.frog.kbo_community.domain.auth.usecase.RenewAccessTokenUsecase;
import com.frog.kbo_community.domain.auth.usecase.dto.LoginCommand;
import com.frog.kbo_community.domain.auth.usecase.dto.RenewAccessTokenCommand;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
	private final LoginUsecase loginUsecase;
	private final RenewAccessTokenUsecase renewAccessTokenUsecase;

	@Autowired
	public AuthController(LoginUsecase loginUsecase, RenewAccessTokenUsecase renewAccessTokenUsecase) {
		this.loginUsecase = loginUsecase;
		this.renewAccessTokenUsecase = renewAccessTokenUsecase;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		var result = loginUsecase.execute(
			LoginCommand.builder()
				.email(request.getEmail())
				.password(request.getPassword())
				.build()
		);

		log.info("로그인 성공 (email: {})", request.getEmail());

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				LoginResponse.builder()
					.accessToken(result.getAccessToken())
					.refreshToken(result.getRefreshToken())
					.build()
			);
	}

	@PostMapping(path = "/new-access-token")
	public ResponseEntity<RenewAccessTokenResponse> renewAccessToken(@CookieValue("REFRESH_TOKEN") String refreshToken) {
		var result = renewAccessTokenUsecase.execute(
			RenewAccessTokenCommand.builder()
				.refreshToken(refreshToken)
				.build()
		);

		return ResponseEntity.status(HttpStatus.CREATED).body(
			RenewAccessTokenResponse.builder()
				.accessToken(result.getAccessToken())
				.build()
		);
	}
}
