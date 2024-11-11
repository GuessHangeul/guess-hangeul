package com.estsoft.guesshangeul.board.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.guesshangeul.board.dto.BoardResponse;
import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.dto.GeneralBoardResponse;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.dto.QuizBoardResponse;
import com.estsoft.guesshangeul.board.service.BoardService;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;
import com.estsoft.guesshangeul.board.service.QuizBoardService;
import com.estsoft.guesshangeul.post.dto.GeneralPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.service.GeneralPostService;
import com.estsoft.guesshangeul.post.service.QuizPostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardViewController {
	private final BoardService boardService;
	private final GeneralPostService generalPostService;
	private final GeneralBoardService generalBoardService;
	private final QuizPostService quizPostService;
	private final QuizBoardService quizBoardService;

	@GetMapping("/createBoard")
	public String createBoard() {
		return "createBoard";
	}

	@GetMapping("/")
	public String home(Model model) {
		List<BoardResponse> boardResponses = boardService.readTotalBoard(Pageable.ofSize(6));
		model.addAttribute("boards", boardResponses);
		return "index";
	}

	@GetMapping("/generalBoard/{generalBoardId}")
	public String generalBoardPage(Model model, @PathVariable Long generalBoardId,
		@RequestParam(value = "search", required = false) String title,
		@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		// 현재 게시판 정보 조회
		model.addAttribute("currentBoard", generalBoardService.findByBoardId(generalBoardId));

		// GeneralBoard 목록 조회
		List<GeneralBoardDto> result = generalBoardService.findAllGeneralBoardByIsDeleted(false, Pageable.unpaged());
		List<GeneralBoardResponse> boardResponses = result.stream().map(GeneralBoardResponse::new).toList();
		model.addAttribute("boardList", boardResponses);

		// GeneralPost 목록 조회
		List<GeneralPostWithCommentCountResponse> postResponses;
		if (title == null) {
			postResponses = generalPostService.getAllGeneralPostsWithCommentCount(
				generalBoardId, pageable);
		} else {
			postResponses = generalPostService.getAllGeneralPostsByTitleWithCommentCount(
				generalBoardId, title, pageable);
		}
		model.addAttribute("posts", postResponses);

		// 페이지 정보
		int currentPage = pageable.getPageNumber();
		int totalPages = postResponses.size() / pageable.getPageSize();
		// 페이지 부족 시 테스트
		// int totalPages = 10;
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);

		return "generalBoard";
	}

	@GetMapping("/quizBoard/{quizBoardId}")
	public String quizBoardPage(Model model, @PathVariable Long quizBoardId,
		@RequestParam(value = "search", required = false) String title,
		@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		// 현재 게시판 정보 조회
		model.addAttribute("currentBoard", quizBoardService.findByBoardId(quizBoardId));

		// QuizBoard 목록 조회
		List<QuizBoardDto> result = quizBoardService.findAllQuizBoardByIsDeleted(false, Pageable.unpaged());
		List<QuizBoardResponse> boardResponses = result.stream().map(QuizBoardResponse::new).toList();
		model.addAttribute("boardList", boardResponses);

		// QuizPost 목록 조회
		List<QuizPostWithCommentCountResponse> postResponses;
		if (title == null) {
			postResponses = quizPostService.getAllQuizPostsWithCommentCount(
				quizBoardId, pageable);
		} else {
			postResponses = quizPostService.getAllQuizPostsByTitleWithCommentCount(
				quizBoardId, title, pageable);
		}
		model.addAttribute("posts", postResponses);

		// 페이지 정보
		int currentPage = pageable.getPageNumber();
		int totalPages = postResponses.size() / pageable.getPageSize();
		// 페이지 부족 시 테스트
		// int totalPages = 10;
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);

		return "quizBoard";
	}
}