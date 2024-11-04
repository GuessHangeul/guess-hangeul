package com.estsoft.guesshangeul.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.users.DTO.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
}
