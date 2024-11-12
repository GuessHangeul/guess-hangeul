package com.estsoft.guesshangeul.post.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.user.entity.Users;

public interface QuizPostWithCommentCountInterface {
	Long getId();

	Users getUsers();

	QuizBoard getQuizBoard();

	String getTitle();

	String getContent();

	Boolean getIsHidden();

	Long getView();

	LocalDateTime getCreatedAt();

	LocalDateTime getUpdatedAt();

	Long getCommentCount();
}