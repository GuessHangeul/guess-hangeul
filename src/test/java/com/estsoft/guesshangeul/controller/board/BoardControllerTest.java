package com.estsoft.guesshangeul.controller.board;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.estsoft.guesshangeul.board.controller.BoardController;
import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;
import com.estsoft.guesshangeul.board.service.QuizBoardService;
import com.estsoft.guesshangeul.user.entity.Users;

@WebMvcTest(controllers = BoardController.class)
@WithMockUser
public class BoardControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GeneralBoardService generalBoardService;

	@MockBean
	private QuizBoardService quizBoardService;

	@Test
	void testReadAllExistingGeneralBoardSuccess() throws Exception {
		// given
		Users users = new Users(1L, "example@email.com");
		GeneralBoard generalBoard = new GeneralBoard("title1", false);
		QuizBoard quizBoard = new QuizBoard("title2", users, false);
		List<GeneralBoardDto> generalBoardDtos = List.of(new GeneralBoardDto(generalBoard));
		List<QuizBoardDto> quizBoardDtos = List.of(new QuizBoardDto(quizBoard));

		when(generalBoardService.findAllGeneralBoardByIsDeleted(eq(false), any(Pageable.class)))
			.thenReturn(generalBoardDtos);
		when(quizBoardService.findAllQuizBoardByIsDeleted(eq(false), any(Pageable.class)))
			.thenReturn(quizBoardDtos);

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/board")
			.accept(MediaType.APPLICATION_JSON)).andDo(print());

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].title").value("title1"))
			.andExpect(jsonPath("$[0].isDeleted").value(false))
			.andExpect(jsonPath("$[1].title").value("title2"))
			.andExpect(jsonPath("$[1].isDeleted").value(false));
	}
}
