package com.estsoft.guesshangeul.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.user.entity.PasswordResetToken;
import com.estsoft.guesshangeul.user.entity.Users;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	Optional<PasswordResetToken> findByToken(String token);

	@Transactional(rollbackFor = Exception.class)
	void deleteByUser(Users user);
}