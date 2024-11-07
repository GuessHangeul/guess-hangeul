package com.estsoft.guesshangeul.userrank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.userrank.dto.RankupUpdateRequest;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;

@Service
public class ViewRankupRequService {
	private BoardManagerRepository repo;
	public ViewRankupRequService(BoardManagerRepository repo) {
		this.repo = repo;
	}
	public List<BoardManagerApply> findAll(){//집현전 신청 받은 내용을 보여주기
		return repo.findAll();
	}
}
