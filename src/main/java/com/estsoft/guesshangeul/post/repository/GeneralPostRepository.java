package com.estsoft.guesshangeul.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.post.entity.GeneralPost;

@Repository
public interface GeneralPostRepository extends JpaRepository<GeneralPost, Long> {
	List<GeneralPost> findByGeneralBoardIdAndIdIn(Long generalBoardId, List<Long> id);

	List<GeneralPost> findTop5ByGeneralBoardIdOrderByCreatedAtDesc(Long generalBoardId);

	List<GeneralPost> findByGeneralBoardId(Long generalBoardId);

	Optional<GeneralPost> findByGeneralBoardIdAndId(Long generalBoardId, Long id);

	Optional<GeneralPost> findByGeneralBoardIdAndTitle(Long generalBoardId, String title);
}