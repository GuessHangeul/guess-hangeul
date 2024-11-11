package com.estsoft.guesshangeul.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.post.dto.AddGeneralPostRequest;
import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.dto.UpdateGeneralPostRequest;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.repository.GeneralPostRepository;

@Service
public class GeneralPostService {
	private final GeneralPostRepository generalPostRepository;

	public GeneralPostService(GeneralPostRepository generalPostRepository) {
		this.generalPostRepository = generalPostRepository;
	}

	// 모든 게시글 조회
	public List<GeneralPostResponse> getAllGeneralPosts(Long generalBoardId) {
		List<GeneralPost> posts = generalPostRepository.findByGeneralBoardId(generalBoardId);
		return posts.stream()
			.map(GeneralPostResponse::new)
			.toList();
	}

	// ID로 게시글 조회 (없으면 예외 발생) + 제목 게시글 구현해야함
	public GeneralPostResponse getGeneralPostById(Long id) {
		GeneralPost post = generalPostRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
		return new GeneralPostResponse(post);
	}

	// 제목으로 게시글 조회
	public GeneralPostResponse getGeneralPostByTitle(String title) {
		GeneralPost post = generalPostRepository.findByTitle(title)
			.orElseThrow(() -> new RuntimeException("해당 제목의 게시글은 존재하지 않습니다."));
		return new GeneralPostResponse(post);
	}

	// 게시글 생성
	public GeneralPostResponse createGeneralPost(AddGeneralPostRequest request) {
		GeneralPost post = request.toEntity();
		GeneralPost createdPost = generalPostRepository.save(post);
		return new GeneralPostResponse(createdPost);
	}

	// 게시글 수정
	public GeneralPostResponse updateGeneralPost(Long id, UpdateGeneralPostRequest request) {
		GeneralPost post = generalPostRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setHidden(request.isHidden());
		post.setView(request.getView());
		GeneralPost updatedPost = generalPostRepository.save(post);
		return new GeneralPostResponse(updatedPost);
	}

	// 게시글 삭제
	public void deleteGeneralPost(Long id) {
		generalPostRepository.deleteById(id);
	}
}