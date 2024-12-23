package com.frog.kbo_community.auth.jwt;

import java.util.Date;
import java.util.UUID;

import com.frog.kbo_community.domain.member.constant.PermissionEnum;

public record AccessTokenPayload(UUID memberId, UUID deviceId, PermissionEnum permissionEnum, Date issuedAt) {
}
