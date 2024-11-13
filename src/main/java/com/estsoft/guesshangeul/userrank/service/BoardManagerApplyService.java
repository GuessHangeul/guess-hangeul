package com.estsoft.guesshangeul.userrank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;
import com.estsoft.guesshangeul.userrank.dto.AddUserRankRequest;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;

@Service
public class BoardManagerApplyService {//집현전 신청 받아서 DB에 입력
	private final UsersRepository usersRepository;
	private final BoardManagerRepository boardManagerApplyRepository;

	@Autowired
	public BoardManagerApplyService(UsersRepository usersRepository, BoardManagerRepository boardManagerRepository) {
		this.usersRepository = usersRepository;
		this.boardManagerApplyRepository = boardManagerRepository;
	}
	public BoardManagerApply save(AddUserRankRequest requ) {
		// userId로 Users 객체를 조회
		Users users = usersRepository.findById(requ.getId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + requ.getId()));

		// BoardManagerApply 객체 생성
		BoardManagerApply apply = new BoardManagerApply(null, users, requ.getStatus(), LocalDateTime.now());
		return boardManagerApplyRepository.save(apply);
	}

	public List<BoardManagerApply> findAll() {
		return boardManagerApplyRepository.findAll();
	}

	public void apply(Long userId) {
		// 새로운 BoardManagerApply 생성 및 현재 시간 설정
		BoardManagerApply application = new BoardManagerApply();
		application.setUsers(new Users(userId)); // Users 엔티티가 ID를 사용해 생성자로 초기화 가능해야 합니다.
		application.setCreatedAt(LocalDateTime.now());
		application.setStatus(0); // 초기 상태값

		// 데이터베이스에 저장
		boardManagerApplyRepository.save(application);
	}
}
