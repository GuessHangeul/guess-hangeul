package com.estsoft.guesshangeul.user.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.repository.UsersRepository;

@Service
public class usersService {
	private final UsersRepository repository;
	private final BCryptPasswordEncoder encoder;

	public usersService(UsersRepository repository, BCryptPasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
}
