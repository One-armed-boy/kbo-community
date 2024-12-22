package com.frog.kbo_community.domain.auth.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frog.kbo_community.domain.auth.AuthInfo;

@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfo, UUID> {
}
