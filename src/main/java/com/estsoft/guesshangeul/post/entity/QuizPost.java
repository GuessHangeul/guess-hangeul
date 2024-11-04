package com.estsoft.guesshangeul.post.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
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
@Table(name = "quiz_post")
public class QuizPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_post_id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "quiz_board_id")
	private GeneralBoard generalBoard;

	@Column(name = "quiz_title", nullable = false)
	private String quizTitle;

	@Column(name = "hint_content", nullable = false)
	private String hintContent;

	@Column(nullable = false)
	private String answer;

	@Column(name = "is_hidden", nullable = false)
	@ColumnDefault("false")
	private Boolean isHidden;

	@Column(nullable = false)
	private Long view;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}