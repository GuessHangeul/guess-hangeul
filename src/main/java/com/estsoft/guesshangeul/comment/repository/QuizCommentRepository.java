package com.estsoft.guesshangeul.comment.repository;

import com.estsoft.guesshangeul.comment.entity.QuizComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizCommentRepository extends JpaRepository<QuizComment, Long> {
	List<QuizComment> findByPostId(Long postId);
}