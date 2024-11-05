package com.estsoft.guesshangeul.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.board.dto.QuizBoardCreateRequest;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.repository.QuizBoardRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersDetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuizBoardService {
	private final QuizBoardRepository quizBoardRepository;
	private final UsersDetailsService usersDetailsService;

	public List<QuizBoardDto> findAllQuizBoardByIsDeleted(Boolean isDeleted) {
		List<QuizBoard> quizBoardList = quizBoardRepository.findAllByIsDeleted(isDeleted);
		return quizBoardList.stream().map(QuizBoardDto::new).toList();
	}

	public boolean quizBoardTitleExists(String title) {
		Optional<QuizBoard> quizBoard = quizBoardRepository.findByTitle(title);
		return quizBoard.isPresent();
	}

	public QuizBoardDto addNewQuizBoard(QuizBoardCreateRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails)authentication.getPrincipal()).getUsername();

		Users users = (Users)usersDetailsService.loadUserByUsername(username);
		String title = request.getTitle();
		QuizBoard quizBoard = quizBoardRepository.save(new QuizBoard(title, users, false));

		return new QuizBoardDto(quizBoard);
	}
}
