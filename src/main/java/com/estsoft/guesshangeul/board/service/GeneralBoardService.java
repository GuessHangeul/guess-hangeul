package com.estsoft.guesshangeul.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.board.dto.GeneralBoardResponse;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GeneralBoardService {
	private final GeneralBoardRepository generalBoardRepository;

	public List<GeneralBoardResponse> findAllGeneralBoardByIsDeleted(Boolean isDeleted) {
		List<GeneralBoard> generalBoardList = generalBoardRepository.findAllByIsDeleted(isDeleted);
		return generalBoardList.stream().map(GeneralBoardResponse::new).toList();
	}
}
