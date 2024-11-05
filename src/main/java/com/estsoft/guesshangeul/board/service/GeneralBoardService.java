package com.estsoft.guesshangeul.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GeneralBoardService {
	private final GeneralBoardRepository generalBoardRepository;

	public List<GeneralBoardDto> findAllGeneralBoardByIsDeleted(Boolean isDeleted) {
		List<GeneralBoard> generalBoardList = generalBoardRepository.findAllByIsDeleted(isDeleted);
		return generalBoardList.stream().map(GeneralBoardDto::new).toList();
	}
}
