package com.estsoft.guesshangeul.post.dto;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.user.entity.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddGeneralPostRequest {
	private String title;
	private String content;

	public GeneralPost toEntity(Users user, GeneralBoard generalBoard) {
		GeneralPost generalPost = new GeneralPost();
		generalPost.setTitle(this.title);
		generalPost.setContent(this.content);
		generalPost.setHidden(false);
		generalPost.setView(0L);
		generalPost.setUsers(user);
		generalPost.setGeneralBoard(generalBoard);

		return generalPost;
	}
}