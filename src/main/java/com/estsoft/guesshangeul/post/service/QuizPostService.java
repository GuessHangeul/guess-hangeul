package com.estsoft.guesshangeul.post.service;


import com.estsoft.guesshangeul.post.dto.AddQuizPostRequest;
import com.estsoft.guesshangeul.post.dto.GetHiddenPostResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostResponse;
import com.estsoft.guesshangeul.post.dto.UpdateQuizPostRequest;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizPostService {
    private final QuizPostRepository quizPostRepository;

    public QuizPostService(QuizPostRepository quizPostRepository) {
        this.quizPostRepository = quizPostRepository;
    }
    // 모든 퀴즈 게시글 조회
    public List<QuizPostResponse> getAllQuizPosts(Long quizBoardId) {
        List<QuizPost> posts = quizPostRepository.findByQuizBoardId(quizBoardId);
        return posts.stream()
                .map(QuizPostResponse::new)
                .toList();
    }
    // 퀴즈 게시글 ID로 조회
    public QuizPostResponse getQuizPostById(Long id) {
        QuizPost post = quizPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
        return new QuizPostResponse(post);
    }
    // 퀴즈 제목으로 조회
    public QuizPostResponse getQuizPostByTitle(String quizTitle) {
        QuizPost post = quizPostRepository.findByQuizTitle(quizTitle)
                .orElseThrow(() -> new RuntimeException("해당 제목의 게시글은 존재하지 않습니다."));
        return new QuizPostResponse(post);
    }
    // 퀴즈 게시글 숨김 여부 조회
    public List<GetHiddenPostResponse> getQuizPostByIsHidden(Boolean isHidden) {
        List<QuizPost> posts = quizPostRepository.findByHidden(isHidden);
        return posts.stream()
                .map(post -> new GetHiddenPostResponse(post.isHidden()))
                .collect(Collectors.toList());
    }
    // 퀴즈 게시글 생성
    public QuizPostResponse createQuizPost(AddQuizPostRequest request) {
        QuizPost post = request.toEntity();
        QuizPost createdPost = quizPostRepository.save(post);
        return new QuizPostResponse(createdPost);
    }
    // 퀴즈 게시글 수정
    public QuizPostResponse updateQuizPost(Long id, UpdateQuizPostRequest request) {
        QuizPost post = quizPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
        post.setQuizTitle(request.getQuizTitle());
        post.setHintContent(request.getHintContent());
        post.setHidden(request.isHidden());
        post.setView(request.getView());
        QuizPost updatedPost = quizPostRepository.save(post);
        return new QuizPostResponse(updatedPost);
    }
    // 퀴즈 게시글 삭제
    public void deleteQuizPost(Long id) {
        quizPostRepository.deleteById(id);
    }
}
