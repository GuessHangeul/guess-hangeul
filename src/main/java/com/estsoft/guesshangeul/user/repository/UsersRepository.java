package com.estsoft.guesshangeul.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.user.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);

	Optional<Users> findByNickname(String nickname);

	List<Users> findAllByIsDeleted(Boolean isDeleted, Pageable pageable);

	// 유저 점수 내림차순 정렬
	List<Users> findAllByIsDeletedOrderByScoreDesc(Boolean isDeleted);

	List<Users> findByIsDeletedAndNickname(Boolean isDeleted, String nickname, Pageable pageable);
}
