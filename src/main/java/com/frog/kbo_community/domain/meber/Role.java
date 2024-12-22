package com.frog.kbo_community.domain.meber;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.uuid.UuidValueGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Role {
	@Id
	@UuidGenerator
	@Column(name="role_id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "name", nullable = false, updatable = false)
	private String name;

	@Builder
	public Role(String name) {
		this.name = name;
	}
}
