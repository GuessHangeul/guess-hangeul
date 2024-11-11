package com.estsoft.guesshangeul.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.post.entity.QuizPost;

@Repository
public interface QuizPostRepository extends JpaRepository<QuizPost, Long> {
	List<QuizPost> findByQuizBoardIdAndIdIn(Long quizBoardId, List<Long> id);

	List<QuizPost> findTop5ByQuizBoardIdOrderByCreatedAtDesc(Long quizBoardId);
}