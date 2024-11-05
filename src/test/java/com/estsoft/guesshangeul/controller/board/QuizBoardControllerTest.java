package com.estsoft.guesshangeul.controller.board;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.estsoft.guesshangeul.board.controller.QuizBoardController;
import com.estsoft.guesshangeul.board.dto.QuizBoardCreateRequest;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.service.QuizBoardService;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = QuizBoardController.class)
@WithMockUser
public class QuizBoardControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private QuizBoardService quizBoardService;

	@MockBean
	private UsersService usersService;

	@Test
	void testReadAllExistingQuizBoardSuccess() throws Exception {
		// given
		Users users = new Users(1L, "example@email.com");
		QuizBoard quizBoard = new QuizBoard("title1", users, false);
		List<QuizBoardDto> result = List.of(new QuizBoardDto(quizBoard));
		when(quizBoardService.findAllQuizBoardByIsDeleted(false)).thenReturn(result);

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/quizBoard")
			.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].title").value("title1"));
	}

	@Test
	void testCreateQuizBoardSuccess() throws Exception {
		// given
		Users users = new Users(1L, "example@email.com");
		QuizBoard quizBoard = new QuizBoard("title", users, false);
		QuizBoardDto result = new QuizBoardDto(quizBoard);
		QuizBoardCreateRequest request = new QuizBoardCreateRequest(quizBoard.getTitle());
		String json = objectMapper.writeValueAsString(request);
		when(quizBoardService.addNewQuizBoard(any())).thenReturn(result);

		// when
		ResultActions resultActions = mockMvc.perform(post("/api/quizBoard")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(json)
			.with(csrf()));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("title"));
	}

	@Test
	void testVerifyTitleCreateRequestThrowsException() throws Exception {
		// given
		QuizBoardCreateRequest request = new QuizBoardCreateRequest("duplicateTitle");
		String json = objectMapper.writeValueAsString(request);
		when(quizBoardService.quizBoardTitleExists("duplicateTitle")).thenReturn(true);

		// when & then
		mockMvc.perform(post("/api/quizBoard")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json)
				.with(csrf()))
			.andExpect(status().isConflict());
	}
}
