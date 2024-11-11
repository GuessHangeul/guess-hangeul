package com.estsoft.guesshangeul.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estsoft.guesshangeul.post.entity.GeneralPost;

public interface GeneralPostRepository extends JpaRepository<GeneralPost, Long> {
	List<GeneralPost> findByGeneralBoardIdAndIdIn(Long generalBoardId, List<Long> id);

	Optional<GeneralPost> findByTitle(String title);

	List<GeneralPost> findByGeneralBoardId(Long generalBoardId);

	List<GeneralPost> findTop5ByGeneralBoardIdOrderByCreatedAtDesc(Long generalBoardId);
}