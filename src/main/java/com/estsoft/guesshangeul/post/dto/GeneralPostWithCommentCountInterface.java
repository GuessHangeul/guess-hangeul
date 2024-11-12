package com.estsoft.guesshangeul.post.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.user.entity.Users;

public interface GeneralPostWithCommentCountInterface {
	Long getId();

	Users getUsers();

	GeneralBoard getGeneralBoard();

	String getTitle();

	String getContent();

	Boolean getIsHidden();

	Long getView();

	LocalDateTime getCreatedAt();

	LocalDateTime getUpdatedAt();

	Long getCommentCount();
}