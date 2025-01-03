package com.frog.kbo_community.auth.jwt;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.frog.kbo_community.domain.member.constant.PermissionEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final SecretKey secretKey;

	@Value("${spring.application.name}")
	private String issuer;

	@Value("#{${jwt.access-key-expiration-s} * 1000}")
	private long accessKeyExpirationInMs;

	@Value("#{${jwt.refresh-key-expiration-s} * 1000}")
	private long refreshKeyExpirationInMs;

	@Autowired
	public JwtService(@Value("${jwt.secret-key}") String secretKey) {
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}

	public Claims verifyToken(String jwt) {
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(jwt)
			.getPayload();
	}

	public AccessTokenPayload createAccessTokenPayload(Claims payload) {
		String permissionString = payload.get("permission", String.class);
		PermissionEnum permissionEnum = PermissionEnum.valueOf(permissionString);
		String deviceId = payload.get("deviceId", String.class);

		return new AccessTokenPayload(
			UUID.fromString(payload.getSubject()),
			UUID.fromString(deviceId),
			permissionEnum,
			payload.getIssuedAt()
		);
	}

	public RefreshTokenPayload createRefreshTokenPayload(Claims payload) {
		var deviceId = payload.get("deviceId", String.class);
		return new RefreshTokenPayload(
			UUID.fromString(payload.getSubject()),
			UUID.fromString(deviceId),
			payload.getIssuedAt()
		);
	}

	public String createAccessToken(AccessTokenPayload jwtPayload) {
		return Jwts.builder()
			.subject(jwtPayload.memberId().toString())
			.claim("permission", jwtPayload.permissionEnum().name())
			.claim("deviceId", jwtPayload.deviceId().toString())
			.issuer(issuer)
			.issuedAt(jwtPayload.issuedAt())
			.expiration(new Date(jwtPayload.issuedAt().getTime() + accessKeyExpirationInMs))
			.signWith(secretKey)
			.compact();
	}

	public String createRefreshToken(RefreshTokenPayload jwtPayload) {
		return Jwts.builder()
			.subject(jwtPayload.memberId().toString())
			.claim("deviceId", jwtPayload.deviceId().toString())
			.issuer(issuer)
			.issuedAt(jwtPayload.issuedAt())
			.expiration(new Date(jwtPayload.issuedAt().getTime() + refreshKeyExpirationInMs))
			.signWith(secretKey)
			.compact();
	}
}
