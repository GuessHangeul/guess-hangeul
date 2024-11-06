package com.estsoft.guesshangeul.userrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;

public interface BoardManagerRepository extends JpaRepository<BoardManagerApply, Long> {
	BoardManagerApply findByBoardManagerId(Long boardManagerId);
}
