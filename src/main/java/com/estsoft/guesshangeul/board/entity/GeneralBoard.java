package com.estsoft.guesshangeul.board.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "general_board")
@EntityListeners(AuditingEntityListener.class)
public class GeneralBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "general_board_id")
	private Long id;

	@Column(nullable = false)
	private String title;

	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "is_deleted", nullable = false)
	@ColumnDefault("false")
	private Boolean isDeleted;

	public GeneralBoard(String title, Boolean isDeleted) {
		this.title = title;
		this.isDeleted = isDeleted;
	}
}
