package com.frog.kbo_community.domain.auth.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frog.kbo_community.auth.jwt.RefreshTokenPayload;
import com.frog.kbo_community.domain.auth.AuthInfo;
import com.frog.kbo_community.domain.auth.exception.AuthInfoNotFoundException;
import com.frog.kbo_community.domain.auth.repository.AuthInfoRepository;
import com.frog.kbo_community.domain.member.Member;
import com.frog.kbo_community.domain.member.repository.MemberRepository;
import com.frog.kbo_community.domain.member.service.MemberService;

@Service
@Transactional(readOnly = true)
public class AuthInfoService {
	private AuthInfoRepository authInfoRepo;
	private MemberService memberService;

	@Autowired
	public AuthInfoService(AuthInfoRepository authInfoRepository, MemberService memberService) {
		this.authInfoRepo = authInfoRepository;
		this.memberService = memberService;
	}

	@Transactional
	public AuthInfo createAuthInfo(UUID memberId, String refreshToken, LocalDateTime expiredAt) {
		var member = memberService.getById(memberId);

		return authInfoRepo.save(
			AuthInfo.builder()
				.deviceId(AuthInfo.createDeviceId())
				.refreshToken(refreshToken)
				.member(member)
				.expiredAt(expiredAt)
				.build());
	}

	public AuthInfo getAuthInfoByMemberIdAndDeviceId(UUID memberId, UUID deviceId) {
		var member = memberService.getById(memberId);

		return authInfoRepo.findByMemberAndDeviceId(member, deviceId)
			.orElseThrow(AuthInfoNotFoundException::new);
	}
}
