package com.estsoft.guesshangeul.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.user.dto.Users;

@Repository
public interface usersRepository extends JpaRepository<Users, Long> {
	List<Users> findAllByIsDeleted(Boolean isDeleted);
}
