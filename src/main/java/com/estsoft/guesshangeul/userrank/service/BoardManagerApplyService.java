package com.estsoft.guesshangeul.userrank.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;

@Service
public class BoardManagerApplyService {
	private final UsersRepository usersRepository;
	private final BoardManagerRepository boardManagerApplyRepository;

	@Autowired
	public BoardManagerApplyService(UsersRepository usersRepository, BoardManagerRepository boardManagerRepository) {
		this.usersRepository = usersRepository;
		this.boardManagerApplyRepository = boardManagerRepository;
	}

	public BoardManagerApply createBoardManagerApply(Long userId, int status) {
		// userId로 Users 객체를 조회
		Users users = usersRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));

		// BoardManagerApply 객체 생성
		BoardManagerApply apply = new BoardManagerApply(null, users, status, LocalDateTime.now());
		return boardManagerApplyRepository.save(apply);
	}
}
