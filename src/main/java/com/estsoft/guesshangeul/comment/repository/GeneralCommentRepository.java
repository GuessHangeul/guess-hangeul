package com.estsoft.guesshangeul.comment.repository;

import com.estsoft.guesshangeul.comment.entity.GeneralComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralCommentRepository extends JpaRepository<GeneralComment, Long> {
	List<GeneralComment> findByPostId(Long postId);
}