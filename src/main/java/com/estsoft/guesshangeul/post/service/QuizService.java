package com.estsoft.guesshangeul.post.service;

import com.estsoft.guesshangeul.post.dto.CheckAnswerRequest;
import com.estsoft.guesshangeul.post.dto.CheckAnswerResponse;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

	@Autowired
	private QuizPostRepository quizPostRepository;

	@Autowired
	private UsersRepository usersRepository;

	public CheckAnswerResponse checkAnswer(CheckAnswerRequest request) {
		QuizPost quizPost = quizPostRepository.findById(request.getQuizPostId()).orElse(null);
		Users user = usersRepository.findById(request.getUserId()).orElse(null);

		CheckAnswerResponse response = new CheckAnswerResponse();

		if (quizPost == null || user == null) {
			response.setMessage("퀴즈 포스트 또는 유저를 찾을 수 없습니다.");
			return response;
		}

		if (quizPost.getAnswer().equalsIgnoreCase(request.getAnswer())) {
			user.setScore(user.getScore() + 10); // 점수 10점 추가
			response.setCorrect(true);
			response.setScore(user.getScore());
			response.setMessage("정답입니다! 10점을 획득하셨습니다.");
		} else {
			user.setScore(user.getScore() - 20); // 점수 20점 감점
			response.setCorrect(false);
			response.setScore(user.getScore());
			response.setMessage("오답입니다. 정답은 " + quizPost.getAnswer() + "입니다.");
		}

		usersRepository.save(user); // 유저 점수 저장
		return response;
	}
}
