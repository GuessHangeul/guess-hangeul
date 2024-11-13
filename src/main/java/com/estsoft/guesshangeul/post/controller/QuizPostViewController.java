// src/main/java/com/estsoft/guesshangeul/post/controller/GeneralPostViewController.java
package com.estsoft.guesshangeul.post.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.guesshangeul.comment.dto.CommentResponse;
import com.estsoft.guesshangeul.comment.service.QuizCommentService;
import com.estsoft.guesshangeul.post.dto.AddQuizPostRequest;
import com.estsoft.guesshangeul.post.dto.QuizPostResponse;
import com.estsoft.guesshangeul.post.service.QuizPostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quizBoard/{quizBoardId}")
public class QuizPostViewController {

	private final QuizPostService quizPostService;
	private final QuizCommentService generalCommentService;

	@GetMapping("/create")
	public String showCreateForm(@PathVariable Long quizBoardId, Model model) {
		model.addAttribute("quizBoardId", quizBoardId);
		return "quizPostCreate";
	}

	@PostMapping("/quizPost")
	public String createPost(@PathVariable Long quizBoardId, @ModelAttribute AddQuizPostRequest request) {
		quizPostService.createQuizPost(request, quizBoardId);
		return "redirect:/quizBoard/" + quizBoardId;
	}

	@GetMapping("/newPost")
	public String newPost(@PathVariable Long quizBoardId, Model model) {
		model.addAttribute("quizBoardId", quizBoardId);
		return "quizPostCreate";
	}

	@GetMapping("/quizPost/{id}")
	public String viewGeneralPost(@PathVariable Long id, Model model) {
		// 게시글 데이터
		QuizPostResponse post = quizPostService.getQuizPostById(id);
		model.addAttribute("post", post);

		// 댓글 데이터
		List<CommentResponse> comments = generalCommentService.getComments(id);
		model.addAttribute("comments", comments);

		// 이전, 다음 게시글 ID
		Optional<Long> prevPostId = quizPostService.getPrevId(id);
		Optional<Long> nextPostId = quizPostService.getNextId(id);
		model.addAttribute("prevPostId", prevPostId.orElse(null));
		model.addAttribute("nextPostId", nextPostId.orElse(null));
		return "quizPostPage";
	}

	@GetMapping("/updatePost/{id}")
	public String showUpdateForm(@PathVariable Long quizBoardId, @PathVariable Long id, Model model) {
		QuizPostResponse post = quizPostService.getQuizPostById(id);
		model.addAttribute("quizBoardId", quizBoardId);
		model.addAttribute("post", post);
		return "quizPostUpdate";
	}
}