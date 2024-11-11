package com.estsoft.guesshangeul.post.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountInterface;
import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;

@Service
public class QuizPostService {
	private final QuizPostRepository quizPostRepository;

	public QuizPostService(QuizPostRepository quizPostRepository) {
		this.quizPostRepository = quizPostRepository;
	}

	@Transactional
	public List<QuizPostWithCommentCountResponse> getAllQuizPostsWithCommentCount(Long quizBoardId,
		Pageable pageable) {
		List<QuizPostWithCommentCountInterface> posts = quizPostRepository.findAllWithCommentCount(quizBoardId,
			pageable);
		List<QuizPostWithCommentCountResponse> result = posts.stream()
			.map(QuizPostWithCommentCountResponse::new)
			.toList();

		return result;
	}
}
