package com.estsoft.guesshangeul.userrank.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class boardManagerApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "boarf_manager_apply_id")
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "status")
	private int status;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
}
