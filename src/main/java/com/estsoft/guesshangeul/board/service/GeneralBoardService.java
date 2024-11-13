package com.estsoft.guesshangeul.board.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GeneralBoardService {
	private final GeneralBoardRepository generalBoardRepository;

	public List<GeneralBoardDto> findAllGeneralBoardByIsDeleted(Boolean isDeleted, Pageable pageable) {
		List<GeneralBoard> generalBoardList = generalBoardRepository.findAllByIsDeleted(isDeleted, pageable);
		return generalBoardList.stream().map(GeneralBoardDto::new).toList();
	}

	public GeneralBoardDto findByBoardId(Long boardId) {
		return new GeneralBoardDto(generalBoardRepository.findById(boardId).orElseThrow());
	}

	public GeneralBoardDto findExistingFirstBoard() {
		GeneralBoard generalBoard = generalBoardRepository.findFirstByIsDeletedOrderById(false)
			.orElseThrow(() -> new NoSuchElementException("일반 게시판이 없습니다."));
		return new GeneralBoardDto(generalBoard);
	}
}
