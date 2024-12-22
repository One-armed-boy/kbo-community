package com.frog.kbo_community.domain.member.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frog.kbo_community.domain.member.Permission;
import com.frog.kbo_community.domain.member.constant.PermissionEnum;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
	Optional<Permission> findByPermissionEnum(PermissionEnum permissionEnum);
}
