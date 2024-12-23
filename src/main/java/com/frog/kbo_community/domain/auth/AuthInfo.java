package com.frog.kbo_community.domain.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.frog.kbo_community.domain.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth_infos")
@SQLDelete(sql = "UPDATE auth_infos SET deleted_at = NOW() WHERE member_id = ? AND device_id = ?")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("deleted_at is null")
@Getter
public class AuthInfo {
	@Id
	@Column(name="auth_info_id", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "device_id", updatable = false, nullable = false)
	private UUID deviceId;

	@Column(name = "refresh_token", nullable = false)
	private String refreshToken;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expired_at", nullable = false)
	private LocalDateTime expiredAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private Member member;

	@Builder
	public AuthInfo(UUID deviceId, String refreshToken, LocalDateTime expiredAt, Member member) {
		this.deviceId = deviceId;
		this.refreshToken = refreshToken;
		this.expiredAt = expiredAt;
		this.member = member;
	}

	public static UUID createDeviceId() {
		return UUID.randomUUID();
	}
}
