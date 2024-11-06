package com.estsoft.guesshangeul.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;
import com.estsoft.guesshangeul.board.repository.QuizBoardRepository;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.GeneralPostRepository;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import com.estsoft.guesshangeul.user.entity.Users;
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

	public Users resetNickname(Long userId) {
		Users users = usersRepository.findById(userId).orElseThrow();
		users.setNickname("hangeul_" + users.getId());
		return usersRepository.save(users);
	}

	public GeneralBoard deleteGeneralBoard(Long boardId) {
		GeneralBoard generalBoard = generalBoardRepository.findById(boardId).orElseThrow();
		generalBoard.setIsDeleted(true);
		return generalBoardRepository.save(generalBoard);
	}

	public QuizBoard deleteQuizBoard(Long boardId) {
		QuizBoard quizBoard = quizBoardRepository.findById(boardId).orElseThrow();
		quizBoard.setIsDeleted(true);
		return quizBoardRepository.save(quizBoard);
	}

	public List<GeneralPost> generalPostHide(Long generalBoardId, List<Long> generalPostId) {
		List<GeneralPost> generalPosts = generalPostRepository.findByGeneralBoardIdAndIdIn(generalBoardId,
			generalPostId);
		for (GeneralPost generalPost : generalPosts) {
			generalPost.setHidden(true);
			generalPostRepository.save(generalPost);
		}
		return generalPosts;
	}

	public List<GeneralPost> generalPostUnhide(Long generalBoardId, List<Long> generalPostId) {
		List<GeneralPost> generalPosts = generalPostRepository.findByGeneralBoardIdAndIdIn(generalBoardId,
			generalPostId);
		for (GeneralPost generalPost : generalPosts) {
			generalPost.setHidden(false);
			generalPostRepository.save(generalPost);
		}
		return generalPosts;
	}

	public List<QuizPost> quizPostHide(Long quizBoardId, List<Long> quizPostId) {
		List<QuizPost> quizPosts = quizPostRepository.findByQuizBoardIdAndIdIn(quizBoardId,
			quizPostId);
		for (QuizPost quizPost : quizPosts) {
			quizPost.setIsHidden(true);
			quizPostRepository.save(quizPost);
		}
		return quizPosts;
	}

	public List<QuizPost> quizPostUnhide(Long quizBoardId, List<Long> quizPostId) {
		List<QuizPost> quizPosts = quizPostRepository.findByQuizBoardIdAndIdIn(quizBoardId,
			quizPostId);
		for (QuizPost quizPost : quizPosts) {
			quizPost.setIsHidden(false);
			quizPostRepository.save(quizPost);
		}
		return quizPosts;
	}

}
