package com.estsoft.guesshangeul.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.board.dto.BoardResponse;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;
import com.estsoft.guesshangeul.board.repository.QuizBoardRepository;
import com.estsoft.guesshangeul.comment.repository.GeneralCommentRepository;
import com.estsoft.guesshangeul.comment.repository.QuizCommentRepository;
import com.estsoft.guesshangeul.post.dto.PostResponse;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.GeneralPostRepository;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final GeneralBoardRepository generalBoardRepository;
	private final QuizBoardRepository quizBoardRepository;
	private final GeneralPostRepository generalPostRepository;
	private final QuizPostRepository quizPostRepository;
	private final GeneralCommentRepository generalCommentRepository;
	private final QuizCommentRepository quizCommentRepository;

	@Transactional
	public List<BoardResponse> readTotalBoard(Pageable pageable) {
		// 일반 게시판 요청
		List<GeneralBoard> generalBoardList = generalBoardRepository.findAllByIsDeleted(false, pageable);
		List<BoardResponse> generalBoardResponse = generalBoardList.stream().map(
			board -> {
				List<GeneralPost> generalPosts = generalPostRepository.findTop5ByGeneralBoardIdOrderByCreatedAtDesc(
					board.getId());
				BoardResponse response = new BoardResponse(board);

				List<PostResponse> generalPostResponseList = generalPosts.stream().map(post -> {
					PostResponse postResponse = new PostResponse(post);
					Long commentCount = generalCommentRepository.countByPostId(post.getId());
					postResponse.setCommentCount(commentCount);
					return postResponse;
				}).toList();
				response.setPosts(generalPostResponseList);

				return response;
			}
		).toList();
		List<BoardResponse> result = new ArrayList<>(generalBoardResponse);

		// 나온 게시판 목록 개수 제외하여 나머지 개수만큼 요청
		int offset = generalBoardList.size();
		Pageable nextPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize() - offset,
				pageable.getSort())
			.withPage(pageable.getPageNumber() + (offset / pageable.getPageSize()));

		// 퀴즈 게시판 요청
		List<QuizBoard> quizBoardList = quizBoardRepository.findAllByIsDeleted(false, nextPageable);
		List<BoardResponse> quizBoardResponse = quizBoardList.stream().map(
			board -> {
				List<QuizPost> quizPosts = quizPostRepository.findTop5ByQuizBoardIdOrderByCreatedAtDesc(
					board.getId());
				BoardResponse response = new BoardResponse(board);

				List<PostResponse> quizPostResponseList = quizPosts.stream().map(post -> {
					PostResponse postResponse = new PostResponse(post);
					Long commentCount = quizCommentRepository.countByPostId(post.getId());
					postResponse.setCommentCount(commentCount);
					return postResponse;
				}).toList();
				response.setPosts(quizPostResponseList);

				return response;
			}
		).toList();
		result.addAll(quizBoardResponse);

		return result;
	}
}
