package com.estsoft.guesshangeul.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.user.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
