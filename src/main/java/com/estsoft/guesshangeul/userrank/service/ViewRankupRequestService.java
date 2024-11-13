package com.estsoft.guesshangeul.userrank.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.admin.dto.BoardManagerApplyResponse;
import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.admin.repository.BoardManagerApplyRepository;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;

@Service
public class ViewRankupRequestService {
	private BoardManagerRepository repo;
	private BoardManagerApply apply;
	public ViewRankupRequestService(BoardManagerRepository repo) {
		this.repo = repo;
	}
	public List<BoardManagerApply> findAll(){//집현전 신청 받은 내용을 보여주기
		return repo.findAll();
	}

	public List<BoardManagerApplyResponse> findByUsersNickname(String nickname, Pageable pageable){
		List<BoardManagerApply> list = repo.findByUsersNickname(nickname, pageable);

		return list.stream().map(apply -> new BoardManagerApplyResponse(apply, nickname)).collect(Collectors.toList());
	}
}
