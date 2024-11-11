package com.estsoft.guesshangeul.admin.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estsoft.guesshangeul.admin.service.AdminBoardService;
import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.dto.GeneralBoardResponse;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.dto.QuizBoardResponse;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;
import com.estsoft.guesshangeul.board.service.QuizBoardService;
import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostResponse;
import com.estsoft.guesshangeul.post.service.GeneralPostService;
import com.estsoft.guesshangeul.post.service.QuizPostService;
import com.estsoft.guesshangeul.user.dto.UsersResponse;

@Controller
public class AdminPageController {

	private final AdminBoardService adminBoardService;
	private final GeneralBoardService generalBoardService;
	private final QuizBoardService quizBoardService;
	private final GeneralPostService generalPostService;
	private final QuizPostService quizPostService;

	public AdminPageController(AdminBoardService adminBoardService, GeneralBoardService generalBoardService,
		QuizBoardService quizBoardService, GeneralPostService generalPostService, QuizPostService quizPostService) {
		this.adminBoardService = adminBoardService;
		this.generalBoardService = generalBoardService;
		this.quizBoardService = quizBoardService;
		this.generalPostService = generalPostService;
		this.quizPostService = quizPostService;
	}

	@GetMapping("/admin")
	public String showUser(Model model) {
		List<UsersResponse> usersResponses = adminBoardService.findAllUsersbyIsDeleted(false);

		model.addAttribute("users", usersResponses);

		return "admin";
	}

	@GetMapping("/admin/generalBoard/{boardId}")
	public String showGeneralBoard(@PathVariable Long boardId, Model model, Pageable pageable) {
		model.addAttribute("board", generalBoardService.findByBoardId(boardId));

		// generalBoard 목록 조회
		List<GeneralBoardDto> result = generalBoardService.findAllGeneralBoardByIsDeleted(false, pageable);
		List<GeneralBoardResponse> boardResponses = result.stream().map(GeneralBoardResponse::new).toList();
		model.addAttribute("generalBoard", boardResponses);

		// generalPost 조회
		List<GeneralPostResponse> posts = generalPostService.getAllGeneralPosts(boardId);
		model.addAttribute("posts", posts);

		return "adminGeneralBoard";
	}

	@GetMapping("/admin/quizBoard/{boardId}")
	public String showQuizBoard(@PathVariable Long boardId, Model model, Pageable pageable) {
		model.addAttribute("board", quizBoardService.findByBoardId(boardId));
		// quizBoard 목록 조회
		List<QuizBoardDto> result = quizBoardService.findAllQuizBoardByIsDeleted(false, pageable);
		List<QuizBoardResponse> response = result.stream().map(QuizBoardResponse::new).toList();
		model.addAttribute("quizBoard", response);

		// quizPost 조회
		List<QuizPostResponse> posts = quizPostService.getAllQuizPosts(boardId);
		model.addAttribute("posts", posts);

		return "adminQuizBoard";
	}
}