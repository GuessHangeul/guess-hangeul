package com.estsoft.guesshangeul.admin.controller;;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.List;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.userrank.service.BoardManagerApplyService;

@WebMvcTest(BoardManagerApplyController.class)
class BoardManagerApplyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BoardManagerApplyService service;

	@Test
	public void testGetAllBoardManagerApply() throws Exception {
		// 가상의 테스트 데이터 설정
		Users testUser = new Users();
		testUser.setId(1L);
		testUser.setNickname("testNickname");
		testUser.setScore(100);
		testUser.setCreatedAt(LocalDateTime.now());
		testUser.setConnectCount(10);

		BoardManagerApply testApply = new BoardManagerApply();
		testApply.setId(1L);
		testApply.setUsers(testUser);
		testApply.setStatus(0);
		testApply.setCreatedAt(LocalDateTime.now());

		List<BoardManagerApply> mockList = List.of(testApply);
		Mockito.when(service.findAll()).thenReturn(mockList);

		// /boardManagerApply 엔드포인트에 GET 요청을 보내고 상태, 모델, 뷰 등을 검증
		mockMvc.perform(get("/boardManagerApply"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("list"))
			.andExpect(view().name("RankupRequestList"));
	}
}