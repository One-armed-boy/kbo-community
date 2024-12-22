package com.frog.kbo_community.domain.auth.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frog.kbo_community.auth.jwt.RefreshTokenPayload;
import com.frog.kbo_community.domain.auth.AuthInfo;
import com.frog.kbo_community.domain.auth.repository.AuthInfoRepository;
import com.frog.kbo_community.domain.member.Member;

@Service
@Transactional(readOnly = true)
public class AuthInfoService {
	private AuthInfoRepository authInfoRepo;

	public AuthInfoService(AuthInfoRepository authInfoRepository) {
		this.authInfoRepo = authInfoRepository;
	}

	@Transactional
	public AuthInfo createAuthInfo(Member member, String refreshToken, LocalDateTime expiredAt) {
		return this.authInfoRepo.save(
			AuthInfo.builder()
				.deviceId(AuthInfo.createDeviceId())
				.refreshToken(refreshToken)
				.member(member)
				.expiredAt(expiredAt)
				.build());
	}
}
