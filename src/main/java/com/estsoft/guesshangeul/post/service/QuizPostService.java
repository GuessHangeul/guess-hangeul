package com.estsoft.guesshangeul.post.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.repository.QuizBoardRepository;

import com.estsoft.guesshangeul.post.dto.AddQuizPostRequest;
import com.estsoft.guesshangeul.post.dto.GetHiddenPostResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostResponse;
import com.estsoft.guesshangeul.post.dto.UpdateQuizPostRequest;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;

import com.estsoft.guesshangeul.exception.EntityNotFoundException;

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

    // 모든 퀴즈 게시글 조회
    public List<QuizPostResponse> getAllQuizPosts(Long quizBoardId) {
        List<QuizPost> posts = quizPostRepository.findByQuizBoardId(quizBoardId);
        return posts.stream()
                .map(QuizPostResponse::new)
                .toList();
    }

    // 퀴즈 게시글 ID로 조회
    public QuizPostResponse getQuizPostById(Long quizBoardId, Long id) {
        QuizPost post = quizPostRepository.findByQuizBoardIdAndId(quizBoardId, id)
                .orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
        return new QuizPostResponse(post);
    }

    // 퀴즈 제목으로 조회
    public QuizPostResponse getQuizPostByTitle(Long quizBoardId, String quizTitle) {
        QuizPost post = quizPostRepository.findByQuizBoardIdAndQuizTitle(quizBoardId, quizTitle)
                .orElseThrow(() -> new RuntimeException("해당 제목의 게시글은 존재하지 않습니다."));
        return new QuizPostResponse(post);
    }

    // 퀴즈 게시글 숨김 여부 조회
    public List<GetHiddenPostResponse> getQuizPostByIsHidden(Long quizBoardId, boolean isHidden) {
        List<QuizPost> posts = quizPostRepository.findByQuizBoardIdAndHidden(quizBoardId, isHidden);
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

        QuizPost post = request.toEntity();
        post.setUser(users);
        post.setQuizBoard(quizBoardOptional.get());
        QuizPost createdPost = quizPostRepository.save(post);
        return new QuizPostResponse(createdPost);
    }

    // 퀴즈 게시글 수정
    @Transactional
    public QuizPostResponse updateQuizPost(Long quizBoardId, Long id, UpdateQuizPostRequest request) {
        QuizPost post = quizPostRepository.findByQuizBoardIdAndId(quizBoardId, id)
                .orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
        post.setQuizTitle(request.getQuizTitle());
        post.setHintContent(request.getHintContent());
        post.setHidden(request.isHidden());
        post.setView(request.getView());
        QuizPost updatedPost = quizPostRepository.save(post);
        return new QuizPostResponse(updatedPost);
    }

    // 퀴즈 게시글 삭제
    @Transactional
    public void deleteQuizPost(Long quizBoardId, Long id) {
        QuizPost post = quizPostRepository.findByQuizBoardIdAndId(quizBoardId, id)
                .orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
        quizPostRepository.delete(post);
    }
}