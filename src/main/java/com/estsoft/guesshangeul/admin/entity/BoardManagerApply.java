package com.estsoft.guesshangeul.admin.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.estsoft.guesshangeul.user.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "board_manager_apply")
@EntityListeners(AuditingEntityListener.class)
public class BoardManagerApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_manager_apply_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@Column(nullable = false)
	@ColumnDefault("0")
	private int status;

	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public BoardManagerApply(Users users) {
		this.users = users;
	}

	@Builder
	public BoardManagerApply(Long id, Users users, int status, LocalDateTime createdAt) {
		this.id = id;
		this.users = users;
		this.status = status;
		this.createdAt = createdAt;
	}

	@Builder
	public BoardManagerApply(Users users, int status, LocalDateTime createdAt) {
		this.users = users;
		this.status = status;
		this.createdAt = createdAt;
	}

	public void update(Users users, int status, LocalDateTime createdAt) {
		this.users = users;
		this.status = status;
		this.createdAt = createdAt;
	}
}
