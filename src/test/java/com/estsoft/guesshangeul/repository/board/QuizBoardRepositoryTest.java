package com.estsoft.guesshangeul.repository.board;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.board.repository.QuizBoardRepository;
import com.estsoft.guesshangeul.user.entity.Users;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuizBoardRepositoryTest {
	@Autowired
	private QuizBoardRepository quizBoardRepository;

	@BeforeEach
	void setup() {
		quizBoardRepository.deleteAll();
	}

	@Test
	void testFindAllByIsDeletedSuccess() {
		// given
		Users users = new Users(1L, "example@email.com");
		QuizBoard quizBoard1 = new QuizBoard("title1", users, false);
		QuizBoard quizBoard2 = new QuizBoard("title2", users, false);
		QuizBoard quizBoard3 = new QuizBoard("title3", users, true);

		quizBoardRepository.saveAll(List.of(quizBoard1, quizBoard2, quizBoard3));

		// when
		List<QuizBoard> existingQuizBoardList = quizBoardRepository.findAllByIsDeleted(false);
		List<QuizBoard> deletedQuizBoardList = quizBoardRepository.findAllByIsDeleted(true);

		// then
		assertThat(existingQuizBoardList).hasSize(2);
		assertThat(existingQuizBoardList.get(0).getTitle()).isEqualTo("title1");
		assertThat(existingQuizBoardList.get(0).getUsers()).isEqualTo(users);
		assertThat(existingQuizBoardList.get(0).getIsDeleted()).isFalse();

		assertThat(existingQuizBoardList.get(1).getTitle()).isEqualTo("title2");
		assertThat(existingQuizBoardList.get(0).getUsers()).isEqualTo(users);
		assertThat(existingQuizBoardList.get(1).getIsDeleted()).isFalse();

		assertThat(deletedQuizBoardList).hasSize(1);
		assertThat(deletedQuizBoardList.get(0).getTitle()).isEqualTo("title3");
		assertThat(existingQuizBoardList.get(0).getUsers()).isEqualTo(users);
		assertThat(deletedQuizBoardList.get(0).getIsDeleted()).isTrue();
	}

	@Test
	void testFindByTitleSuccess() {
		// given
		Users users = new Users(1L, "example@email.com");
		QuizBoard quizBoard1 = new QuizBoard("title1", users, false);
		QuizBoard quizBoard2 = new QuizBoard("title2", users, false);
		QuizBoard quizBoard3 = new QuizBoard("title3", users, true);

		quizBoardRepository.saveAll(List.of(quizBoard1, quizBoard2, quizBoard3));

		// when
		Optional<QuizBoard> result1 = quizBoardRepository.findByTitle("title1");
		Optional<QuizBoard> result2 = quizBoardRepository.findByTitle("wrongTitle");

		// then
		assertTrue(result1.isPresent());
		assertThat(result1.get().getTitle()).isEqualTo("title1");
		assertThat(result1.get().getUsers()).isEqualTo(users);
		assertFalse(result1.get().getIsDeleted());

		assertTrue(result2.isEmpty());
	}
}
