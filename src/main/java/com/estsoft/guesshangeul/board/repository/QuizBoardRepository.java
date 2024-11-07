package com.estsoft.guesshangeul.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.board.entity.QuizBoard;

@Repository
public interface QuizBoardRepository extends JpaRepository<QuizBoard, Long> {
	List<QuizBoard> findAllByIsDeleted(boolean isDeleted, Pageable pageable);

	Optional<QuizBoard> findByTitle(String title);
}
