package com.estsoft.guesshangeul.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.comment.dto.CommentResponse;
import com.estsoft.guesshangeul.comment.dto.QuizCommentRequest;
import com.estsoft.guesshangeul.comment.entity.QuizComment;
import com.estsoft.guesshangeul.comment.repository.QuizCommentRepository;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

@Service
public class QuizCommentService {
	@Autowired
	private QuizCommentRepository commentRepository;

	@Autowired
	private QuizPostRepository postRepository;

	@Autowired
	private UsersRepository userRepository;

	public List<CommentResponse> getComments(Long postId) {
		List<QuizComment> comments = commentRepository.findByPostId(postId);
		return comments.stream().map(CommentResponse::new).toList();
	}

	public CommentResponse addComment(QuizCommentRequest request) {
		QuizPost post = postRepository.findById(request.getPostId())
			.orElseThrow(() -> new RuntimeException("Post not found"));
		Users user = userRepository.findById(request.getUserId())
			.orElseThrow(() -> new RuntimeException("User not found"));

		QuizComment comment = new QuizComment();
		comment.setContent(request.getContent());
		comment.setPost(post);
		comment.setUser(user);
		comment = commentRepository.save(comment);
		return new CommentResponse(comment);
	}

	public CommentResponse modifyComment(Long commentId, String content) {
		QuizComment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("Comment not found"));
		comment.setContent(content);
		comment = commentRepository.save(comment);
		return new CommentResponse(comment);
	}

	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}