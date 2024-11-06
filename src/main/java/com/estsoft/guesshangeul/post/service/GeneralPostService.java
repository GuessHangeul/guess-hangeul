package com.estsoft.guesshangeul.post.service;

import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.repository.GeneralPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralPostService {
    private final GeneralPostRepository generalPostRepository;

    public GeneralPostService(GeneralPostRepository generalPostRepository) {
        this.generalPostRepository = generalPostRepository;
    }

    // 모든 게시글 조회
    public List<GeneralPostResponse> getAllGeneralPosts() {
        List<GeneralPost> posts = generalPostRepository.findAll();
        return posts.stream()
                .map(GeneralPostResponse::new)
                .collect(Collectors.toList());
    }

    // 게시글 ID로 조회
    public GeneralPostResponse getGeneralPostById(long id) {
        GeneralPost post = generalPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."));
        return new GeneralPostResponse(post);
    }

    // 게시글 생성
    public GeneralPostResponse createGeneralPost(GeneralPost post) {
        GeneralPost createdPost = generalPostRepository.save(post);
        return new GeneralPostResponse(createdPost);
    }

    // 게시글 업데이트
    public GeneralPostResponse updateGeneralPost(Long id, GeneralPost postDetails) {
        GeneralPost post = generalPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."));
        post.setId(postDetails.getId());
        post.setUsers(postDetails.getUsers());
        post.setGeneralBoard(postDetails.getGeneralBoard());
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setHidden(postDetails.isHidden());
        post.setView(postDetails.getView());
        post.setCreatedAt(postDetails.getCreatedAt());
        post.setUpdatedAt(postDetails.getUpdatedAt());
        GeneralPost updatedPost = generalPostRepository.save(post);
        return new GeneralPostResponse(updatedPost);
    }

    // 게시글 삭제
    public void deleteGeneralPost(Long id) {
        generalPostRepository.deleteById(id);
    }
}