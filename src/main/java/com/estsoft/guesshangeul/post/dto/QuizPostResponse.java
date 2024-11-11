package com.estsoft.guesshangeul.post.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.post.entity.QuizPost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizPostResponse {
	private Long id;
	private Long usersId;
	private Long quizBoardId;
	private String quizTitle;
	private String hintContent;
	private String answer;
	private boolean isHidden;
	private Long view;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public QuizPostResponse(QuizPost quizPost) {
		this.id = quizPost.getId();
		this.usersId = quizPost.getUser().getId();
		this.quizBoardId = quizPost.getQuizBoard().getId();
		this.quizTitle = quizPost.getQuizTitle();
		this.hintContent = quizPost.getHintContent();
		this.answer = quizPost.getAnswer();
		this.isHidden = quizPost.isHidden();
		this.view = quizPost.getView();
		this.createdAt = quizPost.getCreatedAt();
		this.updatedAt = quizPost.getUpdatedAt();
	}
}