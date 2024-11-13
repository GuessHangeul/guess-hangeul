package com.estsoft.guesshangeul.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.user.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);

	Optional<Users> findByNickname(String nickname);

	List<Users> findAllByIsDeleted(Boolean isDeleted);

	List<Users> findByIsDeletedFalseOrderByScoreDesc();
}
