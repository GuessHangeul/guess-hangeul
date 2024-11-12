package com.estsoft.guesshangeul.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountInterface;
import com.estsoft.guesshangeul.post.entity.QuizPost;

@Repository
public interface QuizPostRepository extends JpaRepository<QuizPost, Long> {
	List<QuizPost> findByQuizBoardIdAndIdIn(Long quizBoardId, List<Long> id);

	List<QuizPost> findByQuizBoardIdAndIsHidden(Long quizBoardId, boolean isHidden);

	List<QuizPost> findTop5ByQuizBoardIdOrderByCreatedAtDesc(Long quizBoardId);

	@Query("SELECT p.id AS id," +
		" p.user AS users, " +
		"p.quizBoard AS quizBoard, " +
		"p.quizTitle AS title, p.hintContent AS content, p.isHidden AS isHidden, " +
		"p.view AS view, p.createdAt AS createdAt, p.updatedAt AS updatedAt, " +
		"COALESCE(COUNT(c.id), 0) AS commentCount " +
		"FROM QuizPost p " +
		"LEFT JOIN p.user u " +
		"LEFT JOIN p.quizBoard gb " +
		"LEFT JOIN QuizComment c ON p.id = c.post.id " +
		"WHERE p.quizBoard.id = :quizBoardId " +
		"GROUP BY p.id, u.id, gb.id")
	List<QuizPostWithCommentCountInterface> findAllWithCommentCount(Long quizBoardId, Pageable pageable);

	@Query("SELECT p.id AS id," +
		" p.user AS users, " +
		"p.quizBoard AS quizBoard, " +
		"p.quizTitle AS title, p.hintContent AS content, p.isHidden AS isHidden, " +
		"p.view AS view, p.createdAt AS createdAt, p.updatedAt AS updatedAt, " +
		"COALESCE(COUNT(c.id), 0) AS commentCount " +
		"FROM QuizPost p " +
		"LEFT JOIN p.user u " +
		"LEFT JOIN p.quizBoard gb " +
		"LEFT JOIN QuizComment c ON p.id = c.post.id " +
		"WHERE p.quizBoard.id = :quizBoardId AND p.quizTitle LIKE CONCAT('%', :title, '%') " +
		"GROUP BY p.id, u.id, gb.id")
	List<QuizPostWithCommentCountInterface> findAllQuizPostByTitleWithCommentCount(Long quizBoardId, String title,
		Pageable pageable);

	List<QuizPost> findByQuizBoardId(Long quizBoardId);

	Optional<QuizPost> findByQuizBoardIdAndId(Long quizBoardId, Long id);
}