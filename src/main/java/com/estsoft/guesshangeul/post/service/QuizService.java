package com.estsoft.guesshangeul.post.service;

import com.estsoft.guesshangeul.post.dto.CheckAnswerRequest;
import com.estsoft.guesshangeul.post.dto.CheckAnswerResponse;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuizService {

	@Autowired
	private QuizPostRepository quizPostRepository;

	@Autowired
	private UsersRepository userRepository;

	public CheckAnswerResponse checkAnswer(CheckAnswerRequest request) {
		QuizPost quizPost = quizPostRepository.findById(request.getQuizPostId()).get();
		Users user = userRepository.findById(request.getUserId()).get(); //

		CheckAnswerResponse response = new CheckAnswerResponse();

		if (quizPost.getAnswer().equalsIgnoreCase(request.getAnswer())) {
			user.setScore(user.getScore() + 10);
			response.setCorrect(true);
			response.setScore(user.getScore());
			response.setMessage("정답입니다! 10점을 획득하셨습니다.");
		} else {
			response.setCorrect(false);
			response.setScore(user.getScore());
			response.setMessage("오답입니다.");
		}

		userRepository.save(user);
		return response;
	}

	@Transactional
	public CheckAnswerResponse checkCorrectAnswer(CheckAnswerRequest request) {
		QuizPost quizPost = quizPostRepository.findById(request.getQuizPostId()).get();
		Users user = userRepository.findById(request.getUserId()).get();

		CheckAnswerResponse response = new CheckAnswerResponse();

		user.setScore(user.getScore() - 20);
		response.setScore(user.getScore());
		response.setMessage("정답은 " + quizPost.getAnswer() + "입니다.");

		userRepository.save(user);
		return response;
	}
}

