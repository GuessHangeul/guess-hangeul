package com.estsoft.guesshangeul.userrank.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;

public interface BoardManagerRepository extends JpaRepository<BoardManagerApply, Long> {
	List<BoardManagerApply> findByUsersNickname(String nickname, Pageable pageable);
}
