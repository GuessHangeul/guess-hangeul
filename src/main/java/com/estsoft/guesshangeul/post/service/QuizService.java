package com.estsoft.guesshangeul.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.post.dto.CheckAnswerRequest;
import com.estsoft.guesshangeul.post.dto.CheckAnswerResponse;
import com.estsoft.guesshangeul.post.entity.PostUser;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.post.repository.PostUserRepository;
import com.estsoft.guesshangeul.post.repository.QuizPostRepository;
import com.estsoft.guesshangeul.user.entity.Authorities;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.AuthoritiesRepository;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

@Service
public class QuizService {

	@Autowired
	private QuizPostRepository quizPostRepository;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	private PostUserRepository postUserRepository;

	@Transactional
	public CheckAnswerResponse checkAnswer(CheckAnswerRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users users1 = (Users)authentication.getPrincipal();
		List<GrantedAuthority> grantedAuthorityList = users1.getGrantedAuthority();

		QuizPost quizPost = quizPostRepository.findById(request.getQuizPostId()).get();
		Users user = userRepository.findById(request.getUserId()).get();

		// 답 정규화 (공백 제거)
		request.setAnswer(request.getAnswer().replace(" ", ""));

		CheckAnswerResponse response = new CheckAnswerResponse();

		if (quizPost.getAnswer().equalsIgnoreCase(request.getAnswer())) {
			Optional<PostUser> postUser = postUserRepository.findByQuizPostAndUser(quizPost, user);

			if (postUser.isPresent()) {
				// 이미 맞춘 문제 -> 점수 변화 x
				response.setMessage("정답입니다! 이미 맞춘 문제이므로 점수는 변하지 않습니다.");
				response.setCorrect(true);
				response.setScore(user.getScore());
			} else {
				// 정답 기록 저장
				postUserRepository.save(new PostUser(quizPost, user));

				// 새로 맞춘 문제 -> 점수 상승
				int previousScore = user.getScore();
				user.setScore(previousScore + 10);

				// 평민 등급을 가지고 있는지 체크
				boolean hasRole = false;
				for (GrantedAuthority grantedAuthority : grantedAuthorityList) {
					hasRole = grantedAuthority.getAuthority().contains("ROLE_PYEONGMIN");
				}

				// 점수가 100점 이상이 되는 순간에 권한 승급
				if (previousScore < 100 && user.getScore() >= 100 && !hasRole) {
					authoritiesRepository.save(new Authorities(user.getId(), "ROLE_PYEONGMIN"));
					response.setMessage("정답입니다! 10점을 획득하셨습니다. \n" + "축하합니다! 100점을 달성하여 평민등급으로 승급하셨습니다!");
				} else {
					response.setMessage("정답입니다! 10점을 획득하셨습니다.");
				}

				response.setCorrect(true);
				response.setScore(user.getScore());
			}
		} else {
			// 오답 응답
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

