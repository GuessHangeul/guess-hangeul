package com.estsoft.guesshangeul.userrank.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRankRequest {
	private Long id;
	private Users users;
	private int status;
	private LocalDateTime createdAt;

	public BoardManagerApply toEntity(){
		return BoardManagerApply.builder().id(id).users(users).status(status).createdAt(createdAt).build();
	}
}
