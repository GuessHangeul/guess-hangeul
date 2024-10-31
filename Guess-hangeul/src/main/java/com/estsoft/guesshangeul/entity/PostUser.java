package com.estsoft.guesshangeul.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "post_user")
public class PostUser {
	@Column(name = "quiz_post_id")
	private QuizPost quizPost;

	@Column(name = "user_id")
	private Long Id;
}
