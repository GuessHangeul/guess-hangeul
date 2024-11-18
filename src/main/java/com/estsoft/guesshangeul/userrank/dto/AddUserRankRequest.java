package com.estsoft.guesshangeul.userrank.dto;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRankRequest {
	private Users users;

	public BoardManagerApply toEntity() {
		BoardManagerApply boardManagerApply = new BoardManagerApply();
		boardManagerApply.setUsers(users);
		return boardManagerApply;
	}
}
