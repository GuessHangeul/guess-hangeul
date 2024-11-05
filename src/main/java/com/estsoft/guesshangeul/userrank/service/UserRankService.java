package com.estsoft.guesshangeul.userrank.service;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.userrank.entity.boardManagerApply;
import com.estsoft.guesshangeul.userrank.dto.AddUserRankRequest;
import com.estsoft.guesshangeul.userrank.repository.boardManagerRepository;

@Service
public class UserRankService {
	public boardManagerApply saveRequest(AddUserRankRequest request) {
		return boardManagerRepository.save(request.toEntity());
	}
}
