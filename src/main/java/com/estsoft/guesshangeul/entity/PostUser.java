package com.estsoft.guesshangeul.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "post_user")
public class PostUser {
	@ManyToOne
	@Column(name = "quiz_post_id")
	private QuizPost quizPost;

	@ManyToOne
	@Column(name = "user_id")
	private Users users;
}
