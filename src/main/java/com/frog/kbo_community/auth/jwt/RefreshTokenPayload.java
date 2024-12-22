package com.frog.kbo_community.auth.jwt;

import java.util.Date;

public record RefreshTokenPayload(String authInfoId, Date issuedAt) {
}
