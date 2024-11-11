package com.estsoft.guesshangeul.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.post.entity.QuizPost;

@Repository
public interface QuizPostRepository extends JpaRepository<QuizPost, Long> {
	List<QuizPost> findByQuizBoardIdAndIdIn(Long quizBoardId, List<Long> id);

	Optional<QuizPost> findByQuizTitle(String quizTitle);

	List<QuizPost> findByHidden(boolean isHidden);

	List<QuizPost> findTop5ByQuizBoardIdOrderByCreatedAtDesc(Long quizBoardId);

	List<QuizPost> findByQuizBoardId(Long quizBoardId);
}