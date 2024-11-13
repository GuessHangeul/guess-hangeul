package com.estsoft.guesshangeul.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.comment.dto.CommentResponse;
import com.estsoft.guesshangeul.comment.dto.GeneralCommentRequest;
import com.estsoft.guesshangeul.comment.entity.GeneralComment;
import com.estsoft.guesshangeul.comment.repository.GeneralCommentRepository;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.repository.GeneralPostRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

@Service
public class GeneralCommentService {
	@Autowired
	private GeneralCommentRepository commentRepository;

	@Autowired
	private GeneralPostRepository postRepository;

	@Autowired
	private UsersRepository userRepository;

	public List<CommentResponse> getComments(Long postId) {
		List<GeneralComment> comments = commentRepository.findByPostId(postId);
		return comments.stream().map(CommentResponse::new).toList();
	}

	public CommentResponse addComment(GeneralCommentRequest request) {
		GeneralPost post = postRepository.findById(request.getPostId())
			.orElseThrow(() -> new RuntimeException("Post not found"));
		Users user = userRepository.findById(request.getUserId())
			.orElseThrow(() -> new RuntimeException("User not found"));

		GeneralComment comment = new GeneralComment();
		comment.setContent(request.getContent());
		comment.setPost(post);
		comment.setUsers(user);
		comment = commentRepository.save(comment);
		return new CommentResponse(comment);
	}

	public CommentResponse modifyComment(Long commentId, String content) {
		GeneralComment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("Comment not found"));
		comment.setContent(content);
		comment = commentRepository.save(comment);
		return new CommentResponse(comment);
	}

	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}
