package com.estsoft.guesshangeul.post.controller;

import com.estsoft.guesshangeul.post.dto.CheckAnswerRequest;
import com.estsoft.guesshangeul.post.dto.CheckAnswerResponse;
import com.estsoft.guesshangeul.post.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/checkanswer")
	public ResponseEntity<CheckAnswerResponse> checkAnswer(@RequestBody CheckAnswerRequest request) {
		CheckAnswerResponse response = quizService.checkAnswer(request);
		return ResponseEntity.ok(response);
	}
}
