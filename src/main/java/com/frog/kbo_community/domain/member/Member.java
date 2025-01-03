package com.frog.kbo_community.domain.member;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "members")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
	@Id
	@UuidGenerator
	@Column(name = "member_id", nullable = false, updatable = false)
	private UUID id;

	@Setter
	@Column(name = "email", nullable = false)
	private String email;

	@Setter
	@Column(name = "decrypted_password")
	private String decryptedPassword;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="permission_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private Permission permission;

	@Builder
	public Member(String email, String decryptedPassword, Permission permission) {
		this.email = email;
		this.decryptedPassword = decryptedPassword;
		this.permission = permission;
	}
}
