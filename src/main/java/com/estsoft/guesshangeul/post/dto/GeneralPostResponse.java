package com.estsoft.guesshangeul.post.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.post.entity.GeneralPost;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "일반 게시글 응답")
public class GeneralPostResponse {
	@Schema(description = "게시글 ID", type = "Long")
	private Long id;
	@Schema(description = "유저 ID", type = "Long")
	private Long userId;
	@Schema(description = "유저 닉네임", type = "String")
	private String nickname;
	@Schema(description = "일반 게시판 ID", type = "Long")
	private Long generalBoardId;
	@Schema(description = "게시글 제목", type = "String")
	private String title;
	@Schema(description = "게시글 내용", type = "String")
	private String content;
	@Schema(description = "게시글 숨김 여부", type = "boolean")
	private boolean isHidden;
	@Schema(description = "게시글 조회수", type = "Long")
	private Long view;
	@Schema(description = "게시글 생성 날짜", type = "String")
	private String createdAt;
	@Schema(description = "게시글 업데이트 날짜", type = "String")
	private String updatedAt;

	public GeneralPostResponse(GeneralPost generalPost) {
		this.id = generalPost.getId();
		this.userId = generalPost.getUsers().getId();
		this.nickname = generalPost.getUsers().getNickname();
		this.generalBoardId = generalPost.getGeneralBoard().getId();
		this.title = generalPost.getTitle();
		this.content = generalPost.getContent();
		this.isHidden = generalPost.isHidden();
		this.view = generalPost.getView();
		this.createdAt = generalPost.getCreatedAt().format(formatter);
		this.updatedAt = generalPost.getUpdatedAt().format(formatter);
	}
}