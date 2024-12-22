package com.frog.kbo_community.domain.auth.usecase;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frog.kbo_community.auth.jwt.AccessTokenPayload;
import com.frog.kbo_community.auth.jwt.JwtService;
import com.frog.kbo_community.auth.jwt.RefreshTokenPayload;
import com.frog.kbo_community.domain.auth.AuthInfo;
import com.frog.kbo_community.domain.auth.exception.InvalidLoginRequestException;
import com.frog.kbo_community.domain.auth.repository.AuthInfoRepository;
import com.frog.kbo_community.domain.auth.service.AuthInfoService;
import com.frog.kbo_community.domain.member.service.MemberService;
import com.frog.kbo_community.domain.auth.usecase.dto.LoginCommand;
import com.frog.kbo_community.domain.auth.usecase.dto.LoginResult;

@Service
public class LoginUsecase {
	@Value("#{${jwt.refresh-key-expiration-s} * 1000}")
	private long refreshKeyExpirationInMs;

	private final MemberService memberService;
	private final AuthInfoService authInfoService;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public LoginUsecase(
		MemberService memberService,
		AuthInfoService authInfoService,
		JwtService jwtService,
		PasswordEncoder passwordEncoder
	) {
		this.memberService = memberService;
		this.authInfoService = authInfoService;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public LoginResult execute(LoginCommand command) {
		var email = command.getEmail();
		var password = command.getPassword();
		var cmdCreatedAt = command.getCmdCreatedAt();

		var member = this.memberService.getByEmail(email);

		if (!this.passwordEncoder.matches(password, member.getDecryptedPassword())) {
			throw new InvalidLoginRequestException();
		}

		var deviceId = AuthInfo.createDeviceId();
		var issuedAt = Date.from(cmdCreatedAt.atZone(ZoneId.systemDefault()).toInstant());

		var accessToken = this.jwtService.createAccessToken(
			new AccessTokenPayload(
				member.getId(),
				deviceId,
				member.getPermission().getName(),
				issuedAt
			)
		);

		var refreshToken = this.jwtService.createRefreshToken(
			new RefreshTokenPayload(
				member.getId(),
				deviceId,
				issuedAt
			)
		);

		var expiredAt = new Date(issuedAt.getTime() + refreshKeyExpirationInMs);

		this.authInfoService.createAuthInfo(
			member,
			refreshToken,
			expiredAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
		);

		return LoginResult.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}
}
