package com.estsoft.guesshangeul.board.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;

@Repository
public interface GeneralBoardRepository extends JpaRepository<GeneralBoard, Long> {
	List<GeneralBoard> findAllByIsDeleted(boolean isDeleted, Pageable pageable);
}
