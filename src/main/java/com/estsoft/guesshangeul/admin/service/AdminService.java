package com.estsoft.guesshangeul.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.admin.repository.BoardManagerApplyRepository;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;
import com.estsoft.guesshangeul.board.repository.QuizBoardRepository;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.GeneralPostRepository;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import com.estsoft.guesshangeul.user.entity.Authorities;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.AuthoritiesRepository;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

@Service
public class AdminService {
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	private GeneralBoardRepository generalBoardRepository;
	@Autowired
	private QuizBoardRepository quizBoardRepository;
	@Autowired
	private GeneralPostRepository generalPostRepository;
	@Autowired
	private QuizPostRepository quizPostRepository;
	@Autowired
	private BoardManagerApplyRepository boardManagerApplyRepository;
	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	// 유저 닉네임 초기화
	public Users resetNickname(Long userId) {
		Users users = usersRepository.findById(userId).orElseThrow();
		users.setNickname("hangeul_" + users.getId());
		return usersRepository.save(users);
	}

	// 일반 게시판 삭제
	public GeneralBoard deleteGeneralBoard(Long boardId) {
		GeneralBoard generalBoard = generalBoardRepository.findById(boardId).orElseThrow();
		generalBoard.setIsDeleted(true);
		return generalBoardRepository.save(generalBoard);
	}

	// 퀴즈 게시판 삭제
	public QuizBoard deleteQuizBoard(Long boardId) {
		QuizBoard quizBoard = quizBoardRepository.findById(boardId).orElseThrow();
		quizBoard.setIsDeleted(true);
		return quizBoardRepository.save(quizBoard);
	}

	// 일반 게시판 게시글 숨김
	public List<GeneralPost> generalPostHide(Long generalBoardId, List<Long> generalPostId) {
		List<GeneralPost> generalPosts = generalPostRepository.findByGeneralBoardIdAndIdIn(generalBoardId,
			generalPostId);
		for (GeneralPost generalPost : generalPosts) {
			generalPost.setHidden(true);
			generalPostRepository.save(generalPost);
		}
		return generalPosts;
	}

	// 일반 게시판 게시글 숨김 해제
	public List<GeneralPost> generalPostUnhide(Long generalBoardId, List<Long> generalPostId) {
		List<GeneralPost> generalPosts = generalPostRepository.findByGeneralBoardIdAndIdIn(generalBoardId,
			generalPostId);
		for (GeneralPost generalPost : generalPosts) {
			generalPost.setHidden(false);
			generalPostRepository.save(generalPost);
		}
		return generalPosts;
	}

	// 퀴즈 게시판 게시글 숨김
	public List<QuizPost> quizPostHide(Long quizBoardId, List<Long> quizPostId) {
		List<QuizPost> quizPosts = quizPostRepository.findByQuizBoardIdAndIdIn(quizBoardId,
			quizPostId);
		for (QuizPost quizPost : quizPosts) {
			quizPost.setIsHidden(true);
			quizPostRepository.save(quizPost);
		}
		return quizPosts;
	}

	// 퀴즈 게시판 게시글 숨김 해제
	public List<QuizPost> quizPostUnhide(Long quizBoardId, List<Long> quizPostId) {
		List<QuizPost> quizPosts = quizPostRepository.findByQuizBoardIdAndIdIn(quizBoardId,
			quizPostId);
		for (QuizPost quizPost : quizPosts) {
			quizPost.setIsHidden(false);
			quizPostRepository.save(quizPost);
		}
		return quizPosts;
	}

	// 집현전 신청 승인
	public BoardManagerApply acceptBoardManager(Long boardManagerId) {
		BoardManagerApply boardManagerApply = boardManagerApplyRepository.findById(boardManagerId).orElseThrow();
		Long usersId = boardManagerApply.getUsers().getId();
		Authorities authorities = authoritiesRepository.findFirstByUserId(usersId);
		if (boardManagerApply.getStatus() == 0) {
			boardManagerApply.setStatus(1);
			authorities.setAuthority("ROLE_JIPHYEONJEON");
			authoritiesRepository.save(authorities);
		}
		return boardManagerApplyRepository.save(boardManagerApply);
	}

	// 집현전 신청 거부
	public BoardManagerApply rejectBoardManager(Long boardManagerId) {
		BoardManagerApply boardManagerApply = boardManagerApplyRepository.findById(boardManagerId).orElseThrow();
		if (boardManagerApply.getStatus() == 0) {
			boardManagerApply.setStatus(2);
		}
		return boardManagerApplyRepository.save(boardManagerApply);
	}
}
