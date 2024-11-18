package com.estsoft.guesshangeul.post.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.post.entity.QuizPost;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "퀴즈 게시글 응답")
public class QuizPostResponse {
	@Schema(description = "게시글 ID", type = "Long")
	private Long id;
	@Schema(description = "유저 ID", type = "Long")
	private Long usersId;
	@Schema(description = "퀴즈 게시판 ID", type = "Long")
	private Long quizBoardId;
	@Schema(description = "유저 닉네임", type = "String")
	private String nickname;
	@Schema(description = "게시글 제목", type = "String")
	private String quizTitle;
	@Schema(description = "게시글 내용", type = "String")
	private String hintContent;
	@Schema(description = "게시글 정답", type = "String")
	private String answer;
	@Schema(description = "게시글 숨김 여부", type = "boolean")
	private boolean isHidden;
	@Schema(description = "게시글 조회수", type = "Long")
	private Long view;
	@Schema(description = "게시글 생성 날짜", type = "String")
	private String createdAt;
	@Schema(description = "게시글 업데이트 날짜", type = "String")
	private String updatedAt;

	public QuizPostResponse(QuizPost quizPost) {
		this.id = quizPost.getId();
		this.usersId = quizPost.getUser().getId();
		this.quizBoardId = quizPost.getQuizBoard().getId();
		this.nickname = quizPost.getUser().getNickname();
		this.quizTitle = quizPost.getQuizTitle();
		this.hintContent = quizPost.getHintContent();
		this.answer = quizPost.getAnswer();
		this.isHidden = quizPost.isHidden();
		this.view = quizPost.getView();
		this.createdAt = quizPost.getCreatedAt().format(formatter);
		this.updatedAt = quizPost.getUpdatedAt().format(formatter);
	}
}