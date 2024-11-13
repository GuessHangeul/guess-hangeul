package com.estsoft.guesshangeul.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
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
import com.estsoft.guesshangeul.userrank.dto.ViewRankupRequestResponse;
import com.estsoft.guesshangeul.userrank.service.ViewRankupRequestService;

@Controller
public class AdminPageController {

	private final AdminBoardService adminBoardService;
	private final GeneralBoardService generalBoardService;
	private final QuizBoardService quizBoardService;
	private final GeneralPostService generalPostService;
	private final QuizPostService quizPostService;
	private final UsersService usersService;
	private final ViewRankupRequestService viewRankupRequestService;

	public AdminPageController(AdminBoardService adminBoardService, GeneralBoardService generalBoardService,
		QuizBoardService quizBoardService, GeneralPostService generalPostService, QuizPostService quizPostService,
		UsersService usersService, ViewRankupRequestService viewRankupRequestService) {
		this.adminBoardService = adminBoardService;
		this.generalBoardService = generalBoardService;
		this.quizBoardService = quizBoardService;
		this.generalPostService = generalPostService;
		this.quizPostService = quizPostService;
		this.usersService = usersService;
		this.viewRankupRequestService = viewRankupRequestService;
	}

	@GetMapping("/admin")
	public String showUser(Model model,
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
		@RequestParam(value = "size", required = false) Integer size,
		@RequestParam(value = "sort", required = false) String sort,
		@RequestParam(value = "nickname", required = false) String nickname) {
		int pageSize = (size == null) ? Integer.MAX_VALUE : size;

		Pageable pageable;

		if (sort == null) {
			pageable = PageRequest.of(page, pageSize);
		} else {
			String[] sortParams = sort.split(",");
			String sortField = sortParams[0];
			Sort.Direction direction = sortParams.length > 1 && "asc".equalsIgnoreCase(sortParams[1])
				? Sort.Direction.ASC : Sort.Direction.DESC;  // 정렬 방향
			pageable = PageRequest.of(page, pageSize, Sort.by(direction, sortField));
		}
		List<UsersResponse> response;

		if (nickname == null) {
			response = adminBoardService.findAllUsersbyIsDeleted(false, pageable);
		} else {
			response = adminBoardService.findUserByNickname(false, nickname, pageable);
		}
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

	@GetMapping("/admin/boardManagerApply")
	public String showBoardManagerApply(Model model,
		@RequestParam(value = "nickname", required = false) String nickname,
		@PageableDefault(size = 10) Pageable pageable) {
		List<BoardManagerApply> responseList;
		if (nickname == null || nickname.isEmpty()) {
			responseList = viewRankupRequestService.findAll();
		} else {
			responseList = viewRankupRequestService.findByUsersNickname(nickname, pageable);
		}
		List<ViewRankupRequestResponse> list = new ArrayList<>();
		for (BoardManagerApply boardManagerApply : responseList) {
			list.add(usersService.getViewRankupResponse(boardManagerApply.getUsers().getId()));
		}
		model.addAttribute("list", list);
		return "adminBoardManager";
	}
}