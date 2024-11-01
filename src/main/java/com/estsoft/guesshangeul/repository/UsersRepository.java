package com.estsoft.guesshangeul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
