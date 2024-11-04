package com.estsoft.guesshangeul.user.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.repository.usersRepository;

@Service
public class usersService {
	private final usersRepository repository;
	private final BCryptPasswordEncoder encoder;

	public usersService(usersRepository repository, BCryptPasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
}
