package com.estsoft.guesshangeul.post.dto;

import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.util.KoreanInitialExtractor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddQuizPostRequest {
	private String answer;
	private String hintContent;

	public QuizPost toEntity(Users user, QuizBoard quizBoard) {
		QuizPost quizPost = new QuizPost();
		String quizTitle = KoreanInitialExtractor.extractInitials(this.answer);
		quizPost.setQuizTitle(quizTitle);
		quizPost.setAnswer(this.answer.replace(" ", ""));
		quizPost.setHintContent(this.hintContent);
		quizPost.setHidden(false);
		quizPost.setView(0L);
		quizPost.setUser(user);
		quizPost.setQuizBoard(quizBoard);

		return quizPost;
	}
}
