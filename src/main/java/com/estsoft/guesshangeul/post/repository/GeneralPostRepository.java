package com.estsoft.guesshangeul.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.post.entity.GeneralPost;

@Repository
public interface GeneralPostRepository extends JpaRepository<GeneralPost, Long> {
    List<GeneralPost> findByGeneralBoardIdAndIdIn(Long generalBoardId, List<Long> id);
    Optional<GeneralPost> findByTitle(String title);
    List<GeneralPost> findByIsHidden(boolean isHidden);
}