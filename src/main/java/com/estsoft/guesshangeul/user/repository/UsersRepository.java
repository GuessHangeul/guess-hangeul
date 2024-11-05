package com.estsoft.guesshangeul.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estsoft.guesshangeul.user.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);

	Optional<Users> findByNickname(String nickname);
}
