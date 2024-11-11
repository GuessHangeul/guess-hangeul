package com.estsoft.guesshangeul.board.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;

@Repository
public interface GeneralBoardRepository extends JpaRepository<GeneralBoard, Long> {
	List<GeneralBoard> findAllByIsDeleted(boolean isDeleted, Pageable pageable);

	// Fetch Join 활용
	// @Query("""
	// 	SELECT DISTINCT b FROM GeneralBoard b
	// 	JOIN FETCH b.posts p
	// 	JOIN FETCH p.user u
	// 	WHERE p.isHidden = false
	// 	  AND b.id IN (
	// 	      SELECT subB.id FROM GeneralBoard subB
	// 	      WHERE subB.isDeleted = false
	// 	      ORDER BY subB.createdAt DESC
	// 	      LIMIT 6
	// 	  )
	// 	ORDER BY b.createdAt DESC, p.createdAt DESC
	// 	""")
	// List<GeneralBoard> findAllTop6Boards();
}
