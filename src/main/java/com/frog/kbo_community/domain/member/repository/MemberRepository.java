package com.frog.kbo_community.domain.member.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frog.kbo_community.domain.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
	Optional<Member> findByEmail(String email);
}
