package com.estsoft.guesshangeul.post.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.repository.QuizBoardRepository;
import com.estsoft.guesshangeul.exception.EntityNotFoundException;
import com.estsoft.guesshangeul.post.dto.AddQuizPostRequest;
import com.estsoft.guesshangeul.post.dto.GetHiddenPostResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountInterface;
import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.dto.UpdateQuizPostRequest;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersDetailsService;

@Service
public class QuizPostService {

	private final QuizPostRepository quizPostRepository;
	private final UsersDetailsService usersDetailsService;
	private final QuizBoardRepository quizBoardRepository;

	public QuizPostService(QuizPostRepository quizPostRepository,
		UsersDetailsService usersDetailsService,
		QuizBoardRepository quizBoardRepository) {
		this.quizPostRepository = quizPostRepository;
		this.usersDetailsService = usersDetailsService;
		this.quizBoardRepository = quizBoardRepository;
	}

	// 모든 게시글 조회
	@Transactional
	public List<QuizPostWithCommentCountResponse> getAllQuizPostsWithCommentCount(Long quizBoardId, Boolean isHidden,
		Pageable pageable) {
		List<QuizPostWithCommentCountInterface> posts;
		if (isHidden == null) {
			posts = quizPostRepository.findAllWithCommentCount(quizBoardId, pageable);
		} else {
			posts = quizPostRepository.findAllByIsHiddenWithCommentCount(quizBoardId, isHidden, pageable);
		}
		List<QuizPostWithCommentCountResponse> result = posts.stream()
			.map(QuizPostWithCommentCountResponse::new)
			.toList();

		return result;
	}

	// 퀴즈 제목으로 조회
	@Transactional
	public List<QuizPostWithCommentCountResponse> getAllQuizPostsByTitleWithCommentCount(Long generalBoardId,
		String title, Boolean isHidden, Pageable pageable) {
		List<QuizPostWithCommentCountInterface> posts;
		if (isHidden == null) {
			posts = quizPostRepository.findAllByTitleWithCommentCount(generalBoardId, title, pageable);
		} else {
			posts = quizPostRepository.findAllByTitleAndIsHiddenWithCommentCount(generalBoardId, title, isHidden,
				pageable);
		}

		List<QuizPostWithCommentCountResponse> result = posts.stream()
			.map(QuizPostWithCommentCountResponse::new)
			.toList();

		return result;
	}

	// 퀴즈 게시글 ID로 조회
	@Transactional
	public QuizPostResponse getQuizPostById(Long id) {
		QuizPost post = quizPostRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
		long prevView = post.getView();
		post.setView(prevView += 1L);
		return new QuizPostResponse(post);
	}

	// 퀴즈 게시글 숨김 여부 조회
	public List<GetHiddenPostResponse> getQuizPostByIsHidden(Long quizBoardId, boolean isHidden) {
		List<QuizPost> posts = quizPostRepository.findByQuizBoardIdAndIsHidden(quizBoardId, isHidden);
		return posts.stream()
			.map(post -> new GetHiddenPostResponse(post.isHidden()))
			.collect(Collectors.toList());
	}

	// 퀴즈 게시글 생성
	@Transactional
	public QuizPostResponse createQuizPost(AddQuizPostRequest request, Long quizBoardId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username;
		if (authentication.getPrincipal() instanceof UserDetails userDetails) {
			username = userDetails.getUsername();
		} else {
			username = (String)authentication.getPrincipal();
		}
		Users users = (Users)usersDetailsService.loadUserByUsername(username);

		Optional<QuizBoard> quizBoardOptional = quizBoardRepository.findById(quizBoardId);
		if (quizBoardOptional.isEmpty()) {
			throw new EntityNotFoundException("QuizBoard", quizBoardId);
		}

		QuizPost post = request.toEntity(users, quizBoardOptional.get());
		QuizPost createdPost = quizPostRepository.save(post);
		return new QuizPostResponse(createdPost);
	}

	// 퀴즈 게시글 수정
	@Transactional
	public QuizPostResponse updateQuizPost(Long id, UpdateQuizPostRequest request) {
		QuizPost post = quizPostRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
		post.setQuizTitle(request.getQuizTitle());
		post.setHintContent(request.getHintContent());
		QuizPost updatedPost = quizPostRepository.save(post);
		return new QuizPostResponse(updatedPost);
	}

	// 퀴즈 게시글 삭제
	@Transactional
	public void deleteQuizPost(Long quizBoardId, Long id) {
		QuizPost post = quizPostRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
		quizPostRepository.delete(post);
	}

	// 퀴즈 게시글 id List로 받아서 삭제
	@Transactional
	public void deleteQuizPostByIdIn(Long quizBoardId, List<Long> postId) {
		quizPostRepository.deleteByQuizBoardIdAndIdIn(quizBoardId, postId);
	}

	public Optional<Long> getPrevId(Long id) {
		return quizPostRepository.findPrevId(id);
	}

	public Optional<Long> getNextId(Long id) {
		return quizPostRepository.findNextId(id);
	}
}
