package com.estsoft.guesshangeul.service.board;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.repository.GeneralBoardRepository;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;

@SpringBootTest
@Transactional
public class GeneralBoardServiceTest {
	@Autowired
	private GeneralBoardService generalBoardService;

	@Autowired
	private GeneralBoardRepository generalBoardRepository;

	@Test
	void testFindAllGeneralBoardByIsDeletedSuccess() {
		// given
		GeneralBoard generalBoard1 = new GeneralBoard("title1", false);
		GeneralBoard generalBoard2 = new GeneralBoard("title2", false);
		GeneralBoard generalBoard3 = new GeneralBoard("title3", true);

		generalBoardRepository.saveAll(List.of(generalBoard1, generalBoard2, generalBoard3));

		// when
		List<GeneralBoardDto> existingGeneralBoardList = generalBoardService.findAllGeneralBoardByIsDeleted(false);
		List<GeneralBoardDto> deletedGeneralBoardList = generalBoardService.findAllGeneralBoardByIsDeleted(true);

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
