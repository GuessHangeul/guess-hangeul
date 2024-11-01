package com.estsoft.guesshangeul.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.estsoft.guesshangeul.entity.QuizBoard;
import com.estsoft.guesshangeul.repository.board.QuizBoardRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuizBoardRepositoryTest {
	@Autowired
	private QuizBoardRepository quizBoardRepository;

	@Test
	void testFindAllByIsDeletedSuccess() {
		// given
		QuizBoard quizBoard1 = new QuizBoard("title1", 1L, LocalDateTime.of(2024, 11, 1, 9, 0), false);
		QuizBoard quizBoard2 = new QuizBoard("title2", 2L, LocalDateTime.of(2023, 1, 2, 3, 4), false);
		QuizBoard quizBoard3 = new QuizBoard("title3", 3L, LocalDateTime.of(2024, 11, 1, 9, 0), true);

		quizBoardRepository.saveAll(List.of(quizBoard1, quizBoard2, quizBoard3));

		// when
		List<QuizBoard> existingQuizBoardList = quizBoardRepository.findAllByIsDeleted(false);
		List<QuizBoard> deletedQuizBoardList = quizBoardRepository.findAllByIsDeleted(true);

		// then
		assertThat(existingQuizBoardList).hasSize(2);
		assertThat(existingQuizBoardList.get(0).getTitle()).isEqualTo("title1");
		assertThat(existingQuizBoardList.get(0).getUserId()).isEqualTo(1L);
		assertThat(existingQuizBoardList.get(0).getCreatedAt()).isEqualTo(LocalDateTime.of(2024, 11, 1, 9, 0));
		assertThat(existingQuizBoardList.get(0).getIsDeleted()).isFalse();

		assertThat(existingQuizBoardList.get(1).getTitle()).isEqualTo("title2");
		assertThat(existingQuizBoardList.get(1).getUserId()).isEqualTo(2L);
		assertThat(existingQuizBoardList.get(1).getCreatedAt()).isEqualTo(LocalDateTime.of(2023, 1, 2, 3, 4));
		assertThat(existingQuizBoardList.get(1).getIsDeleted()).isFalse();

		assertThat(deletedQuizBoardList).hasSize(1);
		assertThat(deletedQuizBoardList.get(0).getTitle()).isEqualTo("title3");
		assertThat(deletedQuizBoardList.get(0).getUserId()).isEqualTo(3L);
		assertThat(deletedQuizBoardList.get(0).getCreatedAt()).isEqualTo(LocalDateTime.of(2024, 11, 1, 9, 0));
		assertThat(deletedQuizBoardList.get(0).getIsDeleted()).isTrue();
	}
}
