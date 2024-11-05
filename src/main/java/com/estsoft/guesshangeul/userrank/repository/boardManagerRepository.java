package com.estsoft.guesshangeul.userrank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estsoft.guesshangeul.user.entity.Users;

public interface boardManagerRepository extends JpaRepository<Users, Long> {
}
