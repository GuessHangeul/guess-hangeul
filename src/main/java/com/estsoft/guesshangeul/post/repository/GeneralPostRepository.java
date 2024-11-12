package com.estsoft.guesshangeul.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.post.dto.GeneralPostWithCommentCountInterface;
import com.estsoft.guesshangeul.post.entity.GeneralPost;

@Repository
public interface GeneralPostRepository extends JpaRepository<GeneralPost, Long> {
	List<GeneralPost> findByGeneralBoardIdAndIdIn(Long generalBoardId, List<Long> id);

	List<GeneralPost> findTop5ByGeneralBoardIdOrderByCreatedAtDesc(Long generalBoardId);

	// 댓글수 추가한 모든 게시글
	@Query("""
		    SELECT p.id AS id,
		           p.users AS users,
		           p.generalBoard AS generalBoard,
		           p.title AS title,
		           p.content AS content,
		           p.isHidden AS isHidden,
		           p.view AS view,
		           p.createdAt AS createdAt,
		           p.updatedAt AS updatedAt,
		           COALESCE(COUNT(c.id), 0) AS commentCount
		    FROM GeneralPost p
		    LEFT JOIN p.users u
		    LEFT JOIN p.generalBoard gb
		    LEFT JOIN GeneralComment c ON p.id = c.post.id
		    WHERE p.generalBoard.id = :generalBoardId
		    GROUP BY p.id, u.id, gb.id
		""")
	List<GeneralPostWithCommentCountInterface> findAllWithCommentCount(Long generalBoardId, Pageable pageable);

	// 숨김 여부 필터링한 댓글수 추가한 게시글
	@Query("""
		    SELECT p.id AS id,
		           p.users AS users,
		           p.generalBoard AS generalBoard,
		           p.title AS title,
		           p.content AS content,
		           p.isHidden AS isHidden,
		           p.view AS view,
		           p.createdAt AS createdAt,
		           p.updatedAt AS updatedAt,
		           COALESCE(COUNT(c.id), 0) AS commentCount
		    FROM GeneralPost p
		    LEFT JOIN p.users u
		    LEFT JOIN p.generalBoard gb
		    LEFT JOIN GeneralComment c ON p.id = c.post.id
		    WHERE p.generalBoard.id = :generalBoardId AND p.isHidden = :isHidden
		    GROUP BY p.id, u.id, gb.id
		""")
	List<GeneralPostWithCommentCountInterface> findAllByIsHiddenWithCommentCount(Long generalBoardId, Boolean isHidden,
		Pageable pageable);

	// 댓글수 추가하고 제목 검색한 게시글
	@Query("""
		    SELECT p.id AS id,
		           p.users AS users,
		           p.generalBoard AS generalBoard,
		           p.title AS title,
		           p.content AS content,
		           p.isHidden AS isHidden,
		           p.view AS view,
		           p.createdAt AS createdAt,
		           p.updatedAt AS updatedAt,
		           COALESCE(COUNT(c.id), 0) AS commentCount
		    FROM GeneralPost p
		    LEFT JOIN p.users u
		    LEFT JOIN p.generalBoard gb
		    LEFT JOIN GeneralComment c ON p.id = c.post.id
		    WHERE p.generalBoard.id = :generalBoardId AND p.title LIKE CONCAT('%', :title, '%')
		    GROUP BY p.id, u.id, gb.id
		""")
	List<GeneralPostWithCommentCountInterface> findAllByTitleWithCommentCount(Long generalBoardId, String title,
		Pageable pageable);

	// 숨김 여부 필터링하고 제목 검색한 댓글수 추가된 게시글
	@Query("""
		    SELECT p.id AS id,
		           p.users AS users,
		           p.generalBoard AS generalBoard,
		           p.title AS title,
		           p.content AS content,
		           p.isHidden AS isHidden,
		           p.view AS view,
		           p.createdAt AS createdAt,
		           p.updatedAt AS updatedAt,
		           COALESCE(COUNT(c.id), 0) AS commentCount
		    FROM GeneralPost p
		    LEFT JOIN p.users u
		    LEFT JOIN p.generalBoard gb
		    LEFT JOIN GeneralComment c ON p.id = c.post.id
		    WHERE p.generalBoard.id = :generalBoardId AND p.title LIKE CONCAT('%', :title, '%') AND p.isHidden = :isHidden
		    GROUP BY p.id, u.id, gb.id
		""")
	List<GeneralPostWithCommentCountInterface> findAllByTitleAndIsHiddenWithCommentCount(Long generalBoardId,
		String title, Boolean isHidden, Pageable pageable);

	List<GeneralPost> findByGeneralBoardId(Long generalBoardId);

	Optional<GeneralPost> findByGeneralBoardIdAndId(Long generalBoardId, Long id);

	Optional<GeneralPost> findByGeneralBoardIdAndTitle(Long generalBoardId, String title);

	void deleteByGeneralBoardIdAndIdIn(Long generalBoardId, List<Long> id);
}