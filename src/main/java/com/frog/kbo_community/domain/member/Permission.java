package com.frog.kbo_community.domain.member;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.frog.kbo_community.domain.member.constant.PermissionEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Permission {
	@Id
	@UuidGenerator
	@Column(name="permission_id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "name", nullable = false, updatable = false)
	private PermissionEnum name;

	@Builder
	public Permission(PermissionEnum name) {
		this.name = name;
	}
}
