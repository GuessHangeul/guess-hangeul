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

	// 댓글수 추가한 모든 게시글
	@Query("""
		    SELECT p.id AS id,
		           p.user AS users,
		           p.quizBoard AS quizBoard,
		           p.quizTitle AS title,
		           p.hintContent AS content,
		           p.isHidden AS isHidden,
		           p.view AS view,
		           p.createdAt AS createdAt,
		           p.updatedAt AS updatedAt,
		           COALESCE(COUNT(c.id), 0) AS commentCount
		    FROM QuizPost p
		    LEFT JOIN p.user u
		    LEFT JOIN p.quizBoard gb
		    LEFT JOIN QuizComment c ON p.id = c.post.id
		    WHERE p.quizBoard.id = :quizBoardId
		    GROUP BY p.id, u.id, gb.id
		""")
	List<QuizPostWithCommentCountInterface> findAllWithCommentCount(Long quizBoardId, Pageable pageable);

	// 숨김 여부 필터링한 댓글수 추가한 게시글
	@Query("""
		    SELECT p.id AS id,
		           p.user AS users,
		           p.quizBoard AS quizBoard,
		           p.quizTitle AS title,
		           p.hintContent AS content,
		           p.isHidden AS isHidden,
		           p.view AS view,
		           p.createdAt AS createdAt,
		           p.updatedAt AS updatedAt,
		           COALESCE(COUNT(c.id), 0) AS commentCount
		    FROM QuizPost p
		    LEFT JOIN p.user u
		    LEFT JOIN p.quizBoard gb
		    LEFT JOIN QuizComment c ON p.id = c.post.id
		    WHERE p.quizBoard.id = :quizBoardId AND p.isHidden = :isHidden
		    GROUP BY p.id, u.id, gb.id
		""")
	List<QuizPostWithCommentCountInterface> findAllByIsHiddenWithCommentCount(Long quizBoardId, Boolean isHidden,
		Pageable pageable);

	// 댓글수 추가하고 제목 검색한 게시글
	@Query("""
		    SELECT p.id AS id,
		           p.user AS users,
		           p.quizBoard AS quizBoard,
		           p.quizTitle AS title,
		           p.hintContent AS content,
		           p.isHidden AS isHidden,
		           p.view AS view,
		           p.createdAt AS createdAt,
		           p.updatedAt AS updatedAt,
		           COALESCE(COUNT(c.id), 0) AS commentCount
		    FROM QuizPost p
		    LEFT JOIN p.user u
		    LEFT JOIN p.quizBoard gb
		    LEFT JOIN QuizComment c ON p.id = c.post.id
		    WHERE p.quizBoard.id = :quizBoardId AND p.quizTitle LIKE CONCAT('%', :title, '%')
		    GROUP BY p.id, u.id, gb.id
		""")
	List<QuizPostWithCommentCountInterface> findAllByTitleWithCommentCount(Long quizBoardId, String title,
		Pageable pageable);

	// 숨김 여부 필터링하고 제목 검색한 댓글수 추가된 게시글
	@Query("""
		    SELECT p.id AS id,
		           p.user AS users,
		           p.quizBoard AS quizBoard,
		           p.quizTitle AS title,
		           p.hintContent AS content,
		           p.isHidden AS isHidden,
		           p.view AS view,
		           p.createdAt AS createdAt,
		           p.updatedAt AS updatedAt,
		           COALESCE(COUNT(c.id), 0) AS commentCount
		    FROM QuizPost p
		    LEFT JOIN p.user u
		    LEFT JOIN p.quizBoard gb
		    LEFT JOIN QuizComment c ON p.id = c.post.id
		    WHERE p.quizBoard.id = :quizBoardId AND p.quizTitle LIKE CONCAT('%', :title, '%') AND p.isHidden = :isHidden
		    GROUP BY p.id, u.id, gb.id
		""")
	List<QuizPostWithCommentCountInterface> findAllByTitleAndIsHiddenWithCommentCount(Long quizBoardId, String title,
		Boolean isHidden, Pageable pageable);

	List<QuizPost> findByQuizBoardId(Long quizBoardId);

	void deleteByQuizBoardIdAndIdIn(Long quizBoardId, List<Long> id);

	Optional<QuizPost> findByQuizBoardIdAndId(Long quizBoardId, Long id);
}