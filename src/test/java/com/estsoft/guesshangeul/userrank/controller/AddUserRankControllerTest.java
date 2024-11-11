package com.estsoft.guesshangeul.userrank.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AddUserRankControllerTest {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private WebApplicationContext web;
	@Autowired
	private BoardManagerRepository repo;
	@BeforeEach
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(web).build();
		repo.deleteAll();
	}
	@DisplayName("신청 추가 성공")
	@Test
	public void testFindAllrequ() throws Exception {
		final String url = "/api/BoardManagerApply";
		final Long id = 1L;
		final Users users = new Users();
		final int status = 0;
		final LocalDateTime time = LocalDateTime.now();
		BoardManagerApply apply = repo.save(new BoardManagerApply(id,users,status,time));
		String request = om.writeValueAsString(apply);

		ResultActions result = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(request));

		result.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(apply.getId()));
	}
}