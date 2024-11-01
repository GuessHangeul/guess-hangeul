package com.estsoft.guesshangeul.service.board;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.dto.board.QuizBoardResponse;
import com.estsoft.guesshangeul.entity.QuizBoard;
import com.estsoft.guesshangeul.repository.board.QuizBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuizBoardService {
	private final QuizBoardRepository quizBoardRepository;

	public List<QuizBoardResponse> findAllQuizBoardByIsDeleted(Boolean isDeleted) {
		List<QuizBoard> quizBoardList = quizBoardRepository.findAllByIsDeleted(isDeleted);
		return quizBoardList.stream().map(QuizBoardResponse::new).toList();
	}
}
