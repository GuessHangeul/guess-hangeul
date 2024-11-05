package com.estsoft.guesshangeul.controller.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
	@Autowired
	private MockMvc mockMvc;

	// get /api/admin/initializeNickname/{user_id}
	@Test
	public void testResetNickname() throws Exception {
		// given
		Long user_id = 1L;

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/admin/initializeNickname/" + user_id)
			.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.nickname").value("hangeul_1"));
	}

	// get /api/admin/deleteBoard/generalBoard/{boardId}
	@Test
	public void testDeleteGeneralBoard() throws Exception {
		// given
		Long boardId = 3L;

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/admin/deleteBoard/generalBoard/{boardId}", boardId)
			.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.isDeleted").value(true));
	}

	// get /api/admin/deleteBoard/quizBoard/{boardId}
	@Test
	public void testDeleteQuizBoard() throws Exception {
		// given
		Long boardId = 4L;

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/admin/deleteBoard/quizBoard/{boardId}", boardId)
			.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.isDeleted").value(true));
	}

}