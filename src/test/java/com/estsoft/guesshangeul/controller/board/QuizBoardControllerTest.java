package com.estsoft.guesshangeul.controller.board;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.estsoft.guesshangeul.dto.board.QuizBoardResponse;
import com.estsoft.guesshangeul.entity.QuizBoard;
import com.estsoft.guesshangeul.service.board.QuizBoardService;

@WebMvcTest(controllers = QuizBoardController.class)
@WithMockUser
public class QuizBoardControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuizBoardService quizBoardService;

	@Test
	void testReadAllExistingQuizBoardSuccess() throws Exception {
		// given
		QuizBoard quizBoard = new QuizBoard("title1", 1L, LocalDateTime.of(2024, 11, 1, 9, 0), false);
		List<QuizBoardResponse> result = List.of(new QuizBoardResponse(quizBoard));
		when(quizBoardService.findAllQuizBoardByIsDeleted(false)).thenReturn(result);

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/quizBoard")
			.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].title").value("title1"))
			.andExpect(jsonPath("$[0].userId").value(1L))
			.andExpect(jsonPath("$[0].createdAt").value("2024-11-01T09:00:00"))
			.andExpect(jsonPath("$[0].isDeleted").value(false));
	}
}
