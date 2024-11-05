package com.estsoft.guesshangeul.controller.board;

import static org.mockito.Mockito.*;
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

import com.estsoft.guesshangeul.board.controller.GeneralBoardController;
import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;

@WebMvcTest(controllers = GeneralBoardController.class)
@WithMockUser
public class GeneralBoardControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GeneralBoardService generalBoardService;

	@Test
	void testReadAllExistingGeneralBoardSuccess() throws Exception {
		// given
		GeneralBoard generalBoard = new GeneralBoard("title1", false);
		List<GeneralBoardDto> result = List.of(new GeneralBoardDto(generalBoard));
		when(generalBoardService.findAllGeneralBoardByIsDeleted(false)).thenReturn(result);

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/generalBoard")
			.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].title").value("title1"))
			.andExpect(jsonPath("$[0].isDeleted").value(false));
	}
}
