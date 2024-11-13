package com.estsoft.guesshangeul.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.guesshangeul.admin.service.AdminBoardService;
import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.dto.GeneralBoardResponse;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.dto.QuizBoardResponse;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;
import com.estsoft.guesshangeul.board.service.QuizBoardService;
import com.estsoft.guesshangeul.post.dto.GeneralPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.service.GeneralPostService;
import com.estsoft.guesshangeul.post.service.QuizPostService;
import com.estsoft.guesshangeul.user.dto.UsersResponse;
import com.estsoft.guesshangeul.user.service.UsersService;

@Controller
public class AdminPageController {

	private final AdminBoardService adminBoardService;
	private final GeneralBoardService generalBoardService;
	private final QuizBoardService quizBoardService;
	private final GeneralPostService generalPostService;
	private final QuizPostService quizPostService;
	private final UsersService usersService;

	public AdminPageController(AdminBoardService adminBoardService, GeneralBoardService generalBoardService,
		QuizBoardService quizBoardService, GeneralPostService generalPostService, QuizPostService quizPostService,
		UsersService usersService) {
		this.adminBoardService = adminBoardService;
		this.generalBoardService = generalBoardService;
		this.quizBoardService = quizBoardService;
		this.generalPostService = generalPostService;
		this.quizPostService = quizPostService;
		this.usersService = usersService;
	}

	@GetMapping("/admin")
	public String showUser(Model model) {
		List<UsersResponse> response = adminBoardService.findAllUsersbyIsDeleted(false);
		List<UsersResponse> usersResponses = new ArrayList<>();
		for (UsersResponse user : response) {
			usersResponses.add(usersService.getUserResponse(user.getUserId()));
		}
		model.addAttribute("users", usersResponses);

		return "admin";
	}

	@GetMapping("/admin/generalBoard/{boardId}")
	public String showGeneralBoard(@PathVariable Long boardId, Model model, Pageable pageable,
		@RequestParam(value = "search", required = false) String title,
		@RequestParam(value = "isHidden", required = false) Boolean isHidden) {
		model.addAttribute("board", generalBoardService.findByBoardId(boardId));

		// generalBoard 목록 조회
		List<GeneralBoardDto> result = generalBoardService.findAllGeneralBoardByIsDeleted(false, pageable);
		List<GeneralBoardResponse> boardResponses = result.stream().map(GeneralBoardResponse::new).toList();
		model.addAttribute("generalBoard", boardResponses);

		// generalPost 조회
		List<GeneralPostWithCommentCountResponse> posts;
		if (title == null) {
			posts = generalPostService.getAllGeneralPostsWithCommentCount(
				boardId, isHidden, pageable);
		} else {
			// 제목 검색으로 조회
			posts = generalPostService.getAllGeneralPostsByTitleWithCommentCount(
				boardId, title, isHidden, pageable);
		}
		model.addAttribute("posts", posts);

		return "adminGeneralBoard";
	}

	@GetMapping("/admin/quizBoard/{boardId}")
	public String showQuizBoard(
		Model model, @PathVariable Long boardId, @RequestParam(value = "search", required = false) String title,
		@RequestParam(value = "isHidden", required = false) Boolean isHidden, Pageable pageable) {

		model.addAttribute("board", quizBoardService.findByBoardId(boardId));

		// quizBoard 목록 조회
		List<QuizBoardDto> result = quizBoardService.findAllQuizBoardByIsDeleted(false, pageable);
		List<QuizBoardResponse> response = result.stream().map(QuizBoardResponse::new).toList();
		model.addAttribute("quizBoard", response);

		// quizPost 조회
		List<QuizPostWithCommentCountResponse> posts;
		if (title == null) {
			posts = quizPostService.getAllQuizPostsWithCommentCount(boardId, isHidden, pageable);
		} else {
			// 제목 검색으로 조회
			posts = quizPostService.getAllQuizPostsByTitleWithCommentCount(boardId, title, isHidden, pageable);
		}
		model.addAttribute("posts", posts);
		return "adminQuizBoard";
	}
}