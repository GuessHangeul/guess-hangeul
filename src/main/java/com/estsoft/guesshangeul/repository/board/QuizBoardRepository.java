package com.estsoft.guesshangeul.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.entity.QuizBoard;

@Repository
public interface QuizBoardRepository extends JpaRepository<QuizBoard, Long> {
	List<QuizBoard> findAllByIsDeleted(boolean isDeleted);
}
