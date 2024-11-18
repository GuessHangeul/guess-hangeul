package com.estsoft.guesshangeul.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estsoft.guesshangeul.comment.entity.QuizComment;

public interface QuizCommentRepository extends JpaRepository<QuizComment, Long> {
	List<QuizComment> findByPostId(Long postId);

	Long countByPostId(Long postId);
}