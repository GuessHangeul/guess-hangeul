package com.estsoft.guesshangeul.comment.controller;

import com.estsoft.guesshangeul.comment.entity.GeneralComment;
import com.estsoft.guesshangeul.comment.dto.GeneralCommentRequest;
import com.estsoft.guesshangeul.comment.service.GeneralCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generalBoard/{board_id}/generalPost/{post_id}/comment")
public class GeneralCommentController {
	@Autowired
	private GeneralCommentService commentService;

	@GetMapping
	public ResponseEntity<List<GeneralComment>> getComments(@PathVariable Long post_id) {
		List<GeneralComment> comments = commentService.getComments(post_id);
		return ResponseEntity.ok(comments);
	}

	@PostMapping
	public ResponseEntity<GeneralComment> AddComment(@RequestBody GeneralCommentRequest request) {
		GeneralComment comment = commentService.addComment(request);
		return ResponseEntity.ok(comment);
	}

	@PutMapping("/{comment_id}")
	public ResponseEntity<GeneralComment> ModifyComment(@PathVariable Long comment_id, @RequestBody String content) {
		GeneralComment comment = commentService.modifyComment(comment_id, content);
		return ResponseEntity.ok(comment);
	}

	@DeleteMapping("/{comment_id}")
	public ResponseEntity<Void> DeleteComment(@PathVariable Long comment_id) {
		commentService.deleteComment(comment_id);
		return ResponseEntity.noContent().build();
	}
}
