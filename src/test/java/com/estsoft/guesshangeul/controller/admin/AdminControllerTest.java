package com.estsoft.guesshangeul.controller.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

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

	// get /api/admin/generalBoard/{boardId}/post/changeVisibilityHide
	@Test
	public void testChangeVisibilityHide() throws Exception {
		// given
		Long boardId = 1L;
		List<Long> postIds = Arrays.asList(1L, 3L);

		// when
		ResultActions resultActions = mockMvc
			.perform(get("/api/admin/generalBoard/{boardId}/post/changeVisibilityHide", boardId)
				.queryParam("postId", postIds.stream().map(String::valueOf).toArray(String[]::new))
				.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].hidden").value(true))
			.andExpect(jsonPath("$[1].hidden").value(true));
	}

	// get /api/admin/generalBoard/{boardId}/post/changeVisibilityUnhidden
	@Test
	public void testChangeVisibilityUnhidden() throws Exception {
		// given
		Long boardId = 1L;
		List<Long> postIds = Arrays.asList(1L, 3L);

		// when
		ResultActions resultActions = mockMvc
			.perform(get("/api/admin/generalBoard/{boardId}/post/changeVisibilityUnhidden", boardId)
				.queryParam("postId", postIds.stream().map(String::valueOf).toArray(String[]::new))
				.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].hidden").value(false))
			.andExpect(jsonPath("$[1].hidden").value(false));
	}

	// get /api/admin/quizBoard/{boardId}/post/changeVisibilityHide
	@Test
	public void testQuizChangeVisibilityHide() throws Exception {
		// given
		Long boardId = 1L;
		List<Long> postIds = Arrays.asList(1L, 3L);

		// when
		ResultActions resultActions = mockMvc
			.perform(get("/api/admin/quizBoard/{boardId}/post/changeVisibilityHide", boardId)
				.queryParam("postId", postIds.stream().map(String::valueOf).toArray(String[]::new))
				.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].hidden").value(true))
			.andExpect(jsonPath("$[1].hidden").value(true));
	}

	// get /api/admin/quizBoard/{boardId}/post/changeVisibilityUnhidden
	@Test
	public void testQuizChangeVisibilityUnhidden() throws Exception {
		// given
		Long boardId = 1L;
		List<Long> postIds = Arrays.asList(1L, 3L);

		// when
		ResultActions resultActions = mockMvc
			.perform(get("/api/admin/quizBoard/{boardId}/post/changeVisibilityUnhidden", boardId)
				.queryParam("postId", postIds.stream().map(String::valueOf).toArray(String[]::new))
				.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].hidden").value(false))
			.andExpect(jsonPath("$[1].hidden").value(false));
	}

}