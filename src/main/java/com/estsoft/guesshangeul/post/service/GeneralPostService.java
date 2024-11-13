package com.estsoft.guesshangeul.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;
import com.estsoft.guesshangeul.exception.EntityNotFoundException;
import com.estsoft.guesshangeul.post.dto.AddGeneralPostRequest;
import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.dto.GeneralPostWithCommentCountInterface;
import com.estsoft.guesshangeul.post.dto.GeneralPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.dto.UpdateGeneralPostRequest;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.repository.GeneralPostRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersDetailsService;

@Service
public class GeneralPostService {
	private final GeneralPostRepository generalPostRepository;
	private final UsersDetailsService usersDetailsService;
	private final GeneralBoardRepository generalBoardRepository;

	public GeneralPostService(GeneralPostRepository generalPostRepository, UsersDetailsService usersDetailsService,
		GeneralBoardRepository generalBoardRepository) {
		this.generalPostRepository = generalPostRepository;
		this.usersDetailsService = usersDetailsService;
		this.generalBoardRepository = generalBoardRepository;
	}

	// 모든 게시글 조회
	@Transactional
	public List<GeneralPostWithCommentCountResponse> getAllGeneralPostsWithCommentCount(Long generalBoardId,
		Boolean isHidden,
		Pageable pageable) {
		List<GeneralPostWithCommentCountInterface> posts;
		if (isHidden == null) {
			posts = generalPostRepository.findAllWithCommentCount(generalBoardId, pageable);
		} else {
			posts = generalPostRepository.findAllByIsHiddenWithCommentCount(generalBoardId, isHidden, pageable);
		}

		List<GeneralPostWithCommentCountResponse> result = posts.stream()
			.map(GeneralPostWithCommentCountResponse::new)
			.toList();

		return result;
	}

	// 검색어를 적용한 모든 게시글 조회
	@Transactional
	public List<GeneralPostWithCommentCountResponse> getAllGeneralPostsByTitleWithCommentCount(Long generalBoardId,
		String title, Boolean isHidden, Pageable pageable) {
		List<GeneralPostWithCommentCountInterface> posts;
		if (isHidden == null) {
			posts = generalPostRepository.findAllByTitleWithCommentCount(
				generalBoardId, title, pageable);
		} else {
			posts = generalPostRepository.findAllByTitleAndIsHiddenWithCommentCount(
				generalBoardId, title, isHidden, pageable);
		}

		List<GeneralPostWithCommentCountResponse> result = posts.stream()
			.map(GeneralPostWithCommentCountResponse::new)
			.toList();

		return result;
	}

	// ID로 게시글 조회 (없으면 예외 발생)
	@Transactional(readOnly = true)
	public GeneralPostResponse getGeneralPostById(Long id) {
		GeneralPost post = generalPostRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
		return new GeneralPostResponse(post);
	}

	// 제목으로 게시글 조회
	public GeneralPostResponse getGeneralPostByTitle(Long generalBoardId, String title) {
		GeneralPost post = generalPostRepository.findByGeneralBoardIdAndTitle(generalBoardId, title)
			.orElseThrow(() -> new RuntimeException("해당 제목의 게시글은 존재하지 않습니다."));
		return new GeneralPostResponse(post);
	}

	// 게시글 생성
	@Transactional
	public GeneralPostResponse createGeneralPost(AddGeneralPostRequest request, Long generalBoardId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username;
		if (authentication.getPrincipal() instanceof UserDetails userDetails) {
			username = userDetails.getUsername();
		} else {
			username = (String)authentication.getPrincipal();
		}
		Users users = (Users)usersDetailsService.loadUserByUsername(username);

		Optional<GeneralBoard> generalBoardOptional = generalBoardRepository.findById(generalBoardId);
		if (generalBoardOptional.isEmpty()) {
			throw new EntityNotFoundException("GeneralBoard", generalBoardId);
		}

		request.setView(0L);
		GeneralPost post = request.toEntity();
		post.setUsers(users);
		post.setGeneralBoard(generalBoardOptional.get());
		GeneralPost createdPost = generalPostRepository.save(post);
		return new GeneralPostResponse(createdPost);
	}

	// 게시글 수정
	@Transactional
	public GeneralPostResponse updateGeneralPost(Long id, UpdateGeneralPostRequest request) {
		GeneralPost post = generalPostRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		GeneralPost updatedPost = generalPostRepository.save(post);
		return new GeneralPostResponse(updatedPost);
	}

	// 게시글 삭제
	@Transactional
	public void deleteGeneralPost(Long id) {
		GeneralPost post = generalPostRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
		generalPostRepository.delete(post);
	}

	public void deleteByGeneralPostId(Long generalBoardId, List<Long> postId) {
		generalPostRepository.deleteByGeneralBoardIdAndIdIn(generalBoardId, postId);
	}

	public Optional<Long> getPrevId(Long id) {
		return generalPostRepository.findPreviousId(id);
	}

	public Optional<Long> getNextId(Long id) {
		return generalPostRepository.findNextId(id);
	}
}
