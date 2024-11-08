package com.estsoft.guesshangeul.comment.controller;

import com.estsoft.guesshangeul.comment.entity.QuizComment;
import com.estsoft.guesshangeul.comment.dto.QuizCommentRequest;
import com.estsoft.guesshangeul.comment.service.QuizCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizBoard/{board_id}/quizPost/{post_id}/comment")
public class QuizCommentController {
	@Autowired
	private QuizCommentService commentService;

	@GetMapping
	public ResponseEntity<List<QuizComment>> getComments(@PathVariable Long post_id) {
		List<QuizComment> comments = commentService.getComments(post_id);
		return ResponseEntity.ok(comments);
	}

	@PostMapping
	public ResponseEntity<QuizComment> addComment(@RequestBody QuizCommentRequest request) {
		QuizComment comment = commentService.addComment(request);
		return ResponseEntity.ok(comment);
	}

	@PutMapping("/{comment_id}")
	public ResponseEntity<QuizComment> modifyComment(@PathVariable Long comment_id, @RequestBody String content) {
		QuizComment comment = commentService.modifyComment(comment_id, content);
		return ResponseEntity.ok(comment);
	}

	@DeleteMapping("/{comment_id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long comment_id) {
		commentService.deleteComment(comment_id);
		return ResponseEntity.noContent().build();
	}
}