package com.estsoft.guesshangeul.userrank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;

@Service
public class ViewRankupRequService {
	private BoardManagerRepository repo;
	public ViewRankupRequService(BoardManagerRepository repo) {
		this.repo = repo;
	}
	public List<BoardManagerApply> findAll(){
		return repo.findAll();
	}
}
