package com.estsoft.guesshangeul.user.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
@EntityListeners(AuditingEntityListener.class)
public class Users implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "nickname", nullable = false)
	private String nickname;

	@Column(name = "user_rank", nullable = false)
	@ColumnDefault("0") //디폴트 값 0으로 지정
	private int userrank;

	@Column(nullable = false)
	@ColumnDefault("0")
	private int score;

	@CreatedDate
	@Column(name = "create_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "connected_at", nullable = false)
	private LocalDateTime connectedAt;

	@Column(name = "connect_count", nullable = false)
	private int connectCount;

	@Column(name = "user_agent_value", nullable = false)
	private String userAgent;

	@Column(name = "is_deleted", nullable = false)
	@ColumnDefault("false")
	private boolean isDeleted;

	public Users(Long userId) {
		this.id = userId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}
}
