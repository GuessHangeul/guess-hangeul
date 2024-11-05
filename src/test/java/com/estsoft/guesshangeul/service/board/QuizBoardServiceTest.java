package com.estsoft.guesshangeul.service.board;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.board.dto.QuizBoardCreateRequest;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.repository.QuizBoardRepository;
import com.estsoft.guesshangeul.board.service.QuizBoardService;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersDetailsService;
import com.estsoft.guesshangeul.user.service.UsersService;

@SpringBootTest
@Transactional
public class QuizBoardServiceTest {
	@Autowired
	private QuizBoardService quizBoardService;

	@Autowired
	private QuizBoardRepository quizBoardRepository;

	@MockBean
	private UsersDetailsService usersDetailsService;

	@MockBean
	private UsersService usersService;

	@Test
	void testFindAllQuizBoardByIsDeletedSuccess() {
		// given
		QuizBoard quizBoard1 = new QuizBoard("title1", 1L, false);
		QuizBoard quizBoard2 = new QuizBoard("title2", 2L, false);
		QuizBoard quizBoard3 = new QuizBoard("title3", 3L, true);

		quizBoardRepository.saveAll(List.of(quizBoard1, quizBoard2, quizBoard3));

		// when
		List<QuizBoardDto> existingQuizBoardList = quizBoardService.findAllQuizBoardByIsDeleted(false);
		List<QuizBoardDto> deletedQuizBoardList = quizBoardService.findAllQuizBoardByIsDeleted(true);

		// then
		assertThat(existingQuizBoardList).hasSize(2);
		assertThat(existingQuizBoardList.get(0).getTitle()).isEqualTo("title1");
		assertThat(existingQuizBoardList.get(0).getUserId()).isEqualTo(1L);
		assertThat(existingQuizBoardList.get(0).getIsDeleted()).isFalse();

		assertThat(existingQuizBoardList.get(1).getTitle()).isEqualTo("title2");
		assertThat(existingQuizBoardList.get(1).getUserId()).isEqualTo(2L);
		assertThat(existingQuizBoardList.get(1).getIsDeleted()).isFalse();

		assertThat(deletedQuizBoardList).hasSize(1);
		assertThat(deletedQuizBoardList.get(0).getTitle()).isEqualTo("title3");
		assertThat(deletedQuizBoardList.get(0).getUserId()).isEqualTo(3L);
		assertThat(deletedQuizBoardList.get(0).getIsDeleted()).isTrue();
	}

	@Test
	@WithMockUser
	void testAddNewQuizBoardSuccess() {
		// given
		QuizBoardCreateRequest request = new QuizBoardCreateRequest("title");
		Users users = new Users(1L, "example@email.com");
		String username = anyString();
		when(usersDetailsService.loadUserByUsername(username)).thenReturn(users);
		when(usersService.findUserByEmail(users.getEmail())).thenReturn(users);

		// when
		QuizBoardDto quizBoard = quizBoardService.addNewQuizBoard(request);

		// then
		assertThat(quizBoard.getTitle()).isEqualTo("title");
	}
}
