package com.estsoft.guesshangeul.admin.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import com.estsoft.guesshangeul.user.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "board_manager_apply")
public class BoardManagerApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_manager_apply_id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@Column(nullable = false)
	@ColumnDefault("0")
	private int status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
}
