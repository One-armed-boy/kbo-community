package com.frog.kbo_community.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frog.kbo_community.controller.member.dto.RegisterMemberRequest;
import com.frog.kbo_community.domain.member.usecase.RegisterNormalMemberUsecase;
import com.frog.kbo_community.domain.member.usecase.dto.RegisterMemberCommand;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {
	private RegisterNormalMemberUsecase registerNormalMemberUsecase;

	@Autowired
	public MemberController(RegisterNormalMemberUsecase registerNormalMemberUsecase) {
		this.registerNormalMemberUsecase = registerNormalMemberUsecase;
	}

	@PostMapping
	public ResponseEntity registerNormalMember(@Valid @RequestBody final RegisterMemberRequest request) {
		this.registerNormalMemberUsecase.execute(
			RegisterMemberCommand.builder()
				.email(request.getEmail()).password(request.getPassword()).build()
		);
		log.info("회원 가입 완료 (email: {})", request.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
