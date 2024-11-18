package com.estsoft.guesshangeul.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estsoft.guesshangeul.comment.entity.GeneralComment;

public interface GeneralCommentRepository extends JpaRepository<GeneralComment, Long> {
	List<GeneralComment> findByPostId(Long postId);

	Long countByPostId(Long postId);
}