package com.estsoft.guesshangeul.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estsoft.guesshangeul.post.dto.GeneralPostWithCommentCountInterface;
import com.estsoft.guesshangeul.post.entity.GeneralPost;

public interface GeneralPostRepository extends JpaRepository<GeneralPost, Long> {
	List<GeneralPost> findByGeneralBoardIdAndIdIn(Long generalBoardId, List<Long> id);

	Optional<GeneralPost> findByTitle(String title);

	List<GeneralPost> findByGeneralBoardId(Long generalBoardId);

	List<GeneralPost> findTop5ByGeneralBoardIdOrderByCreatedAtDesc(Long generalBoardId);

	@Query("SELECT p.id AS id," +
		" p.users AS users, " +
		"p.generalBoard AS generalBoard, " +
		"p.title AS title, p.content AS content, p.isHidden AS isHidden, " +
		"p.view AS view, p.createdAt AS createdAt, p.updatedAt AS updatedAt, " +
		"COALESCE(COUNT(c.id), 0) AS commentCount " +
		"FROM GeneralPost p " +
		"LEFT JOIN p.users u " +
		"LEFT JOIN p.generalBoard gb " +
		"LEFT JOIN GeneralComment c ON p.id = c.post.id " +
		"WHERE p.generalBoard.id = :generalBoardId " +
		"GROUP BY p.id, u.id, gb.id")
	List<GeneralPostWithCommentCountInterface> findAllWithCommentCount(Long generalBoardId, Pageable pageable);

	@Query("SELECT p.id AS id," +
		" p.users AS users, " +
		"p.generalBoard AS generalBoard, " +
		"p.title AS title, p.content AS content, p.isHidden AS isHidden, " +
		"p.view AS view, p.createdAt AS createdAt, p.updatedAt AS updatedAt, " +
		"COALESCE(COUNT(c.id), 0) AS commentCount " +
		"FROM GeneralPost p " +
		"LEFT JOIN p.users u " +
		"LEFT JOIN p.generalBoard gb " +
		"LEFT JOIN GeneralComment c ON p.id = c.post.id " +
		"WHERE p.generalBoard.id = :generalBoardId AND p.title LIKE CONCAT('%', :title, '%') " +
		"GROUP BY p.id, u.id, gb.id")
	List<GeneralPostWithCommentCountInterface> findAllByTitleWithCommentCount(Long generalBoardId, String title,
		Pageable pageable);
}