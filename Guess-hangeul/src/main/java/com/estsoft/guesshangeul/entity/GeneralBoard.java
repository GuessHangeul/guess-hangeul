package com.estsoft.guesshangeul.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "general_board")
public class GeneralBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "general_board_id", unique = true, nullable = false)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "is_deleted", nullable = false)
	@ColumnDefault("false")
	private Boolean isDeleted;
}