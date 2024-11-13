package com.estsoft.guesshangeul.userrank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;

@Service
public class ViewRankupRequestService {
	private BoardManagerRepository repo;

	public ViewRankupRequestService(BoardManagerRepository repo) {
		this.repo = repo;
	}

	public List<BoardManagerApply> findAll() {//집현전 신청 받은 내용을 보여주기
		return repo.findAll();
	}
}
