package com.estsoft.guesshangeul.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.DynamicInsert;
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
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class Users implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "nickname", nullable = false)
	private String nickname;

	@Column(nullable = false)
	private int score;

	@CreatedDate
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "connected_at")
	private LocalDateTime connectedAt;

	@Column(name = "connect_count", nullable = false)
	private int connectCount;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	// 권한 리스트
	@Transient
	private ArrayList<GrantedAuthority> grantedAuthority;

	public Users(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public Users(Long userId, String email) {
		this.id = userId;
		this.email = email;
	}

	public void passwordChange(String password) {
		this.password = password;
	}

	public void withdrawal(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void updateConnectedAt() {
		this.connectedAt = LocalDateTime.now();
	}

	public void incrementConnectCount() {
		this.connectCount += 1;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return !isDeleted;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>(grantedAuthority);
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}
}
