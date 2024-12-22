package com.frog.kbo_community.domain.member.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frog.kbo_community.domain.member.constant.PermissionEnum;
import com.frog.kbo_community.domain.member.exception.DuplicatedEmailException;
import com.frog.kbo_community.domain.member.service.MemberService;
import com.frog.kbo_community.domain.member.service.PermissionService;
import com.frog.kbo_community.domain.member.usecase.dto.RegisterMemberCommand;

@Service
public class RegisterNormalMemberUsecase {
	private final PasswordEncoder passwordEncoder;
	private final MemberService memberSrv;
	private final PermissionService permissionSrv;

	@Autowired
	public RegisterNormalMemberUsecase(
		PasswordEncoder passwordEncoder,
		MemberService memberService,
		PermissionService permissionService
	) {
		this.passwordEncoder = passwordEncoder;
		this.memberSrv = memberService;
		this.permissionSrv = permissionService;
	}

	@Transactional
	public void execute(RegisterMemberCommand command) {
		var email = command.getEmail();
		var password = command.getPassword();

		var memberFindByEmail = this.memberSrv.findByEmail(email);

		if (memberFindByEmail.isPresent()) {
			throw new DuplicatedEmailException();
		}

		var permission = this.permissionSrv.getByPermissionEnum(PermissionEnum.NORMAL);

		this.memberSrv.createMember(email, passwordEncoder.encode(password), permission);
	}
}
