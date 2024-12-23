package com.frog.kbo_community.domain.auth.usecase;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frog.kbo_community.auth.jwt.AccessTokenPayload;
import com.frog.kbo_community.auth.jwt.JwtService;
import com.frog.kbo_community.domain.auth.exception.InvalidTokenException;
import com.frog.kbo_community.domain.auth.service.AuthInfoService;
import com.frog.kbo_community.domain.auth.usecase.dto.RenewAccessTokenCommand;
import com.frog.kbo_community.domain.auth.usecase.dto.RenewAccessTokenResult;

@Service
public class RenewAccessTokenUsecase {
	private final JwtService jwtService;
	private final AuthInfoService authInfoService;

	@Autowired
	public RenewAccessTokenUsecase(JwtService jwtService, AuthInfoService authInfoService) {
		this.jwtService = jwtService;
		this.authInfoService = authInfoService;
	}

	@Transactional(readOnly = true)
	public RenewAccessTokenResult execute(RenewAccessTokenCommand command) {
		var refreshToken = command.getRefreshToken();
		var cmdCreatedAt = command.getCmdCreatedAt();

		var refreshTokenPayload = jwtService.createRefreshTokenPayload(
			jwtService.verifyToken(refreshToken)
		);

		var memberId = refreshTokenPayload.memberId();
		var deviceId = refreshTokenPayload.deviceId();

		var authInfo = authInfoService.getAuthInfoByMemberIdAndDeviceId(
			memberId, deviceId
		);

		if (cmdCreatedAt.isAfter(authInfo.getExpiredAt())) {
			throw new InvalidTokenException();
		}

		var newAccessToken = jwtService.createAccessToken(
			new AccessTokenPayload(
				memberId,
				deviceId,
				authInfo.getMember().getPermission().getName(),
				Date.from(cmdCreatedAt.atZone(ZoneId.systemDefault()).toInstant())
			)
		);

		return RenewAccessTokenResult.builder()
			.accessToken(newAccessToken)
			.build();
	}
}
