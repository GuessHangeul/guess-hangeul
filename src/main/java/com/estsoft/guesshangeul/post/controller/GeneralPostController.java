package com.estsoft.guesshangeul.post.controller;

import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.service.GeneralPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/generalBoard/{board_id}/generalPost")
public class GeneralPostController {
    private final GeneralPostService generalPostService;

    public GeneralPostController(GeneralPostService generalPostService) {
        this.generalPostService = generalPostService;
    }

    // 게시글 전체 조회
    @GetMapping("/")
    public ResponseEntity<List<GeneralPostResponse>> getAllGeneralPosts(@PathVariable String board_id) {
        List<GeneralPostResponse> getAllGeneralPosts = generalPostService.getAllGeneralPosts()
                .stream().map(GeneralPostResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(getAllGeneralPosts);
    }

    // 게시글 ID로 조회
    @GetMapping("/{post_id}")
    public ResponseEntity<GeneralPostResponse> getGeneralPostById(@PathVariable Long post_id, @PathVariable String board_id) {
        GeneralPostResponse getGeneralPostById = generalPostService.getGeneralPostById(post_id);
        return ResponseEntity.ok(getGeneralPostById);
    }

    // 게시글 생성
    @PostMapping("/")
    public ResponseEntity<GeneralPostResponse> createGeneralPost(@RequestBody GeneralPost post, @PathVariable String board_id) {
        GeneralPostResponse createGeneralPost = generalPostService.createGeneralPost(post);
        return ResponseEntity.ok(createGeneralPost);
    }

    // 게시글 업데이트
    @PutMapping("/{post_id}")
    public ResponseEntity<GeneralPostResponse> updateGeneralPost(@PathVariable Long post_id, @RequestBody GeneralPost postDetails, @PathVariable String board_id) {
        GeneralPostResponse updateGeneralPost = generalPostService.updateGeneralPost(post_id, postDetails);
        return ResponseEntity.ok(updateGeneralPost);
    }

    // 게시글 삭제
    @DeleteMapping("/{post_id}")
    public ResponseEntity<Void> deleteGeneralPost(@PathVariable Long post_id, @PathVariable String board_id) {
        generalPostService.deleteGeneralPost(post_id);
        return ResponseEntity.noContent().build();
    }
}