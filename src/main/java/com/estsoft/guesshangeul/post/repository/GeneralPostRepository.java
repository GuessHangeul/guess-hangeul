package com.estsoft.guesshangeul.post.repository;

import com.estsoft.guesshangeul.post.entity.GeneralPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralPostRepository extends JpaRepository<GeneralPost, Long> {
}
