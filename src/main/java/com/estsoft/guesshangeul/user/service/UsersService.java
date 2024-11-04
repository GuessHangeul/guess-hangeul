package com.estsoft.guesshangeul.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.exception.UsersNotFoundException;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {
	private final UsersRepository usersRepository;

	public boolean isUserExistsByUserId(Long userId) {
		return usersRepository.existsById(userId);
	}

	@Override
	public Users loadUserByUsername(String email) throws UsernameNotFoundException {
		return usersRepository.findByEmail(email)
			.orElseThrow(() -> new UsersNotFoundException("email", email));
	}
}
