package com.estsoft.guesshangeul.repository.board;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GeneralBoardRepositoryTest {
	@Autowired
	private GeneralBoardRepository generalBoardRepository;

	@Test
	void testFindAllByIsDeletedSuccess() {
		// given
		GeneralBoard generalBoard1 = new GeneralBoard("title1", false);
		GeneralBoard generalBoard2 = new GeneralBoard("title2", false);
		GeneralBoard generalBoard3 = new GeneralBoard("title3", true);

		generalBoardRepository.saveAll(List.of(generalBoard1, generalBoard2, generalBoard3));

		// when
		List<GeneralBoard> existingGeneralBoardList = generalBoardRepository.findAllByIsDeleted(false,
			Pageable.unpaged());
		List<GeneralBoard> deletedGeneralBoardList = generalBoardRepository.findAllByIsDeleted(true,
			Pageable.unpaged());

		// then
		assertThat(existingGeneralBoardList).hasSize(2);
		assertThat(existingGeneralBoardList.get(0).getTitle()).isEqualTo("title1");
		assertThat(existingGeneralBoardList.get(0).getIsDeleted()).isFalse();

		assertThat(existingGeneralBoardList.get(1).getTitle()).isEqualTo("title2");
		assertThat(existingGeneralBoardList.get(1).getIsDeleted()).isFalse();

		assertThat(deletedGeneralBoardList).hasSize(1);
		assertThat(deletedGeneralBoardList.get(0).getTitle()).isEqualTo("title3");
		assertThat(deletedGeneralBoardList.get(0).getIsDeleted()).isTrue();
	}
}
