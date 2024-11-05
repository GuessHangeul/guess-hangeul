package com.estsoft.guesshangeul.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

@Service
public class AdminService {
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	private GeneralBoardRepository generalBoardRepository;

	public Users resetNickname(Long userId) {
		Users users = usersRepository.findById(userId).orElseThrow();
		users.setNickname("hangeul_" + users.getId());
		return usersRepository.save(users);
	}

	public GeneralBoard deleteGeneralBoard(Long boardId) {
		GeneralBoard generalBoard = generalBoardRepository.findById(boardId).orElseThrow();
		generalBoard.setIsDeleted(true);
		return generalBoardRepository.save(generalBoard);
	}

}
