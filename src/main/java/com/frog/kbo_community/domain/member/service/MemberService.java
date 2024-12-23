package com.frog.kbo_community.domain.member.service;

import java.util.Optional;
import java.util.UUID;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frog.kbo_community.domain.member.Member;
import com.frog.kbo_community.domain.member.Permission;
import com.frog.kbo_community.domain.member.exception.MemberNotFoundException;
import com.frog.kbo_community.domain.member.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class MemberService {
	private final MemberRepository memberRepo;

	@Autowired
	public MemberService(MemberRepository memberRepo) {
		this.memberRepo = memberRepo;
	}

	public Member getById(UUID memberId) {
		return memberRepo.findById(memberId).orElseThrow(MemberNotFoundException::new);
	}

	public Optional<Member> findByEmail(String email) {
		return memberRepo.findByEmail(email);
	}

	public Member getByEmail(String email) {
		return findByEmail(email).orElseThrow(MemberNotFoundException::new);
	}

	@Transactional
	public Member createMember(String email, String decryptedPasword, Permission permission) {
		return memberRepo.save(
			Member.builder()
				.email(email)
				.decryptedPassword(decryptedPasword)
				.permission(permission)
				.build()
		);
	}
}
