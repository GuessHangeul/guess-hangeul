package com.estsoft.guesshangeul.userrank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;
import com.estsoft.guesshangeul.user.service.UsersDetailsService;
import com.estsoft.guesshangeul.userrank.dto.ViewRankupRequestResponse;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;

@Service
public class BoardManagerApplyService {//집현전 신청 받아서 DB에 입력
	private final UsersRepository usersRepository;
	private final BoardManagerRepository boardManagerApplyRepository;
	private final UsersDetailsService usersDetailsService;

	@Autowired
	public BoardManagerApplyService(UsersRepository usersRepository, BoardManagerRepository boardManagerRepository,
		UsersDetailsService usersDetailsService) {
		this.usersRepository = usersRepository;
		this.boardManagerApplyRepository = boardManagerRepository;
		this.usersDetailsService = usersDetailsService;
	}

	public List<BoardManagerApply> findAll() {
		return boardManagerApplyRepository.findAll();
	}

	public ViewRankupRequestResponse addBoardManager() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username;
		if (authentication.getPrincipal() instanceof UserDetails userDetails) {
			username = userDetails.getUsername();
		} else {
			username = (String)authentication.getPrincipal();
		}

		Users users = (Users)usersDetailsService.loadUserByUsername(username);
		BoardManagerApply boardManagerApply = boardManagerApplyRepository.save(new BoardManagerApply(users));
		ViewRankupRequestResponse response = new ViewRankupRequestResponse(boardManagerApply);
		return response;
	}

}
