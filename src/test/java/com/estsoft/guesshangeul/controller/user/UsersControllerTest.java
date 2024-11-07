package com.estsoft.guesshangeul.controller.user;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.estsoft.guesshangeul.user.controller.UsersController;
import com.estsoft.guesshangeul.user.dto.CheckEmailExistsRequest;
import com.estsoft.guesshangeul.user.dto.CheckNicknameExistsRequest;
import com.estsoft.guesshangeul.user.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UsersController.class)
@WithMockUser
public class UsersControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UsersService usersService;

	@Test
	void testCheckEmailExistsSuccess() throws Exception {
		// given
		String email1 = "exists@email.com";
		CheckEmailExistsRequest request1 = new CheckEmailExistsRequest(email1);
		String json1 = objectMapper.writeValueAsString(request1);

		String email2 = "not-exists@email.com";
		CheckEmailExistsRequest request2 = new CheckEmailExistsRequest(email2);
		String json2 = objectMapper.writeValueAsString(request2);

		doReturn(true).when(usersService).checkEmailExists(email1);
		doReturn(false).when(usersService).checkEmailExists(email2);

		// when
		ResultActions resultActions1 = mockMvc.perform(post("/api/checkEmailDuplicate")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(json1)
			.with(csrf()));

		ResultActions resultActions2 = mockMvc.perform(post("/api/checkEmailDuplicate")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(json2)
			.with(csrf()));

		// then
		resultActions1.andExpect(status().isOk())
			.andExpect(jsonPath("$.email").value(email1))
			.andExpect(jsonPath("$.isExists").value(true));

		resultActions2.andExpect(status().isOk())
			.andExpect(jsonPath("$.email").value(email2))
			.andExpect(jsonPath("$.isExists").value(false));
	}

	@Test
	void testCheckNicknameExistsSuccess() throws Exception {
		// given
		String nickname1 = "exists123";
		CheckNicknameExistsRequest request1 = new CheckNicknameExistsRequest(nickname1);
		String json1 = objectMapper.writeValueAsString(request1);

		String nickname2 = "not-exists-123";
		CheckNicknameExistsRequest request2 = new CheckNicknameExistsRequest(nickname2);
		String json2 = objectMapper.writeValueAsString(request2);

		doReturn(true).when(usersService).checkNicknameExists(nickname1);
		doReturn(false).when(usersService).checkNicknameExists(nickname2);

		// when
		ResultActions resultActions1 = mockMvc.perform(post("/api/checkNicknameDuplicate")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(json1)
			.with(csrf()));

		ResultActions resultActions2 = mockMvc.perform(post("/api/checkNicknameDuplicate")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(json2)
			.with(csrf()));

		// then
		resultActions1.andExpect(status().isOk())
			.andExpect(jsonPath("$.nickname").value(nickname1))
			.andExpect(jsonPath("$.isExists").value(true));

		resultActions2.andExpect(status().isOk())
			.andExpect(jsonPath("$.nickname").value(nickname2))
			.andExpect(jsonPath("$.isExists").value(false));
	}
}
