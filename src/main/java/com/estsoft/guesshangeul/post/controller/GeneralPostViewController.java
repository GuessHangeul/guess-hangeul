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
import com.estsoft.guesshangeul.comment.service.GeneralCommentService;
import com.estsoft.guesshangeul.post.dto.AddGeneralPostRequest;
import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.service.GeneralPostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/generalBoard/{generalBoardId}")
public class GeneralPostViewController {

	private final GeneralPostService generalPostService;
	private final GeneralCommentService generalCommentService;

	@GetMapping("/create")
	public String showCreateForm(@PathVariable Long generalBoardId, Model model) {
		model.addAttribute("generalBoardId", generalBoardId);
		return "generalPostCreate";
	}

	@PostMapping("/generalPost")
	public String createPost(@PathVariable Long generalBoardId, @ModelAttribute AddGeneralPostRequest request) {
		generalPostService.createGeneralPost(request, generalBoardId);
		return "redirect:/generalBoard/" + generalBoardId;
	}

	@GetMapping("/newPost")
	public String newPost(@PathVariable Long generalBoardId, Model model) {
		model.addAttribute("generalBoardId", generalBoardId);
		return "generalPostCreate";
	}

	@GetMapping("/generalPost/{id}")
	public String viewGeneralPost(@PathVariable Long id, Model model) {
		// 게시글 데이터
		GeneralPostResponse post = generalPostService.getGeneralPostById(id);
		model.addAttribute("post", post);

		// 댓글 데이터
		List<CommentResponse> comments = generalCommentService.getComments(id);
		model.addAttribute("comments", comments);

		// 이전, 다음 게시글 ID
		Optional<Long> prevPostId = generalPostService.getPrevId(id);
		Optional<Long> nextPostId = generalPostService.getNextId(id);
		model.addAttribute("prevPostId", prevPostId.orElse(null));
		model.addAttribute("nextPostId", nextPostId.orElse(null));
		return "generalPostPage";
	}

	@GetMapping("/updatePost/{id}")
	public String showUpdateForm(@PathVariable Long generalBoardId, @PathVariable Long id, Model model) {
		GeneralPostResponse post = generalPostService.getGeneralPostById(id);
		model.addAttribute("generalBoardId", generalBoardId);
		model.addAttribute("post", post);
		return "generalPostUpdate";
	}

	// @PutMapping("/updatePost/{id}")
	// public String updatePost(@PathVariable Long generalBoardId, @PathVariable Long id,
	// 	@ModelAttribute UpdateGeneralPostRequest request) {
	// 	generalPostService.updateGeneralPost(id, request);
	// 	return "redirect:/generalBoard/" + generalBoardId;
	// }
}