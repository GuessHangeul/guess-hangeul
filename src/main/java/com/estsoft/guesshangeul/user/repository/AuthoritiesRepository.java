package com.estsoft.guesshangeul.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estsoft.guesshangeul.user.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
	List<Authorities> findByUserId(Long userId);
}
