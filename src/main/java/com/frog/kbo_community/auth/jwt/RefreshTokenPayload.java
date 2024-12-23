package com.frog.kbo_community.auth.jwt;

import java.util.Date;
import java.util.UUID;

public record RefreshTokenPayload(UUID memberId, UUID deviceId, Date issuedAt) {
}
