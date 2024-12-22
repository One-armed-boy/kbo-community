package com.frog.kbo_community.domain.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frog.kbo_community.domain.member.Permission;
import com.frog.kbo_community.domain.member.constant.PermissionEnum;
import com.frog.kbo_community.domain.member.exception.PermissionNotFoundException;
import com.frog.kbo_community.domain.member.repository.PermissionRepository;

@Service
@Transactional(readOnly = true)
public class PermissionService {
	private final PermissionRepository permissionRepo;

	@Autowired
	public PermissionService(PermissionRepository permissionRepo) {
		this.permissionRepo = permissionRepo;
	}

	public Permission getByPermissionEnum(PermissionEnum permissionEnum) {
		return this.permissionRepo.findByPermissionEnum(permissionEnum)
			.orElseThrow(PermissionNotFoundException::new);
	}
}
