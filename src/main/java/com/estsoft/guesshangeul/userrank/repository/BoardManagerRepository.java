package com.estsoft.guesshangeul.userrank.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;

public interface BoardManagerRepository extends JpaRepository<BoardManagerApply, Long> {
	@Query("SELECT b FROM BoardManagerApply b WHERE b.users.id = :userId")
	BoardManagerApply findByUserId(@Param("userId") Long userId);

	List<BoardManagerApply> findByUsersNickname(String nickname, Pageable pageable);
}
