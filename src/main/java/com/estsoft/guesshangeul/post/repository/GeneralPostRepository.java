package com.estsoft.guesshangeul.post.repository;

import com.estsoft.guesshangeul.post.entity.GeneralPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeneralPostRepository extends JpaRepository<GeneralPost, Long> {
    List<GeneralPost> findByGeneralBoardIdAndIdIn(Long generalBoardId, List<Long> id);
    Optional<GeneralPost> findByTitle(String title);
}