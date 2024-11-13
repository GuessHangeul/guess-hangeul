package com.estsoft.guesshangeul.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.comment.dto.CommentResponse;
import com.estsoft.guesshangeul.comment.dto.QuizCommentRequest;
import com.estsoft.guesshangeul.comment.service.QuizCommentService;

@RestController
@RequestMapping("/api/quizBoard/{board_id}/quizPost/{post_id}/comment")
public class QuizCommentController {
	@Autowired
	private QuizCommentService commentService;

	@GetMapping
	public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long post_id) {
		List<CommentResponse> comments = commentService.getComments(post_id);
		return ResponseEntity.ok(comments);
	}

	@PostMapping
	public ResponseEntity<CommentResponse> addComment(@RequestBody QuizCommentRequest request) {
		CommentResponse comment = commentService.addComment(request);
		return ResponseEntity.ok(comment);
	}

	@PutMapping("/{comment_id}")
	public ResponseEntity<CommentResponse> modifyComment(@PathVariable Long comment_id, @RequestBody String content) {
		CommentResponse comment = commentService.modifyComment(comment_id, content);
		return ResponseEntity.ok(comment);
	}

	@DeleteMapping("/{comment_id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long comment_id) {
		commentService.deleteComment(comment_id);
		return ResponseEntity.noContent().build();
	}
}