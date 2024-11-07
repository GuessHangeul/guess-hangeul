package com.estsoft.guesshangeul.comment.service;

import com.estsoft.guesshangeul.comment.entity.QuizComment;
import com.estsoft.guesshangeul.comment.dto.QuizCommentRequest;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import com.estsoft.guesshangeul.comment.repository.QuizCommentRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizCommentService {
	@Autowired
	private QuizCommentRepository commentRepository;

	@Autowired
	private QuizPostRepository postRepository;

	@Autowired
	private UsersRepository userRepository;

	public List<QuizComment> getComments(Long postId) {
		return commentRepository.findByPostId(postId);
	}

	public QuizComment addComment(QuizCommentRequest request) {
		QuizPost post = postRepository.findById(request.getPostId())
			.orElseThrow(() -> new RuntimeException("Post not found"));
		Users user = userRepository.findById(request.getUserId())
			.orElseThrow(() -> new RuntimeException("User not found"));

		QuizComment comment = new QuizComment();
		comment.setContent(request.getContent());
		comment.setPost(post);
		comment.setUser(user);
		return commentRepository.save(comment);
	}

	public QuizComment modifyComment(Long commentId, String content) {
		QuizComment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("Comment not found"));
		comment.setContent(content);
		return commentRepository.save(comment);
	}

	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}