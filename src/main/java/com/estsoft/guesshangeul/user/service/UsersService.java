package com.estsoft.guesshangeul.user.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.dto.AddAuthorityRequest;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.entity.Authorities;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.AuthoritiesRepository;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
	private final UsersRepository usersRepository;
	private final AuthoritiesRepository authoritiesRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Users save(AddUserRequest request) {
		request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		return usersRepository.save(request.toEntity());
	}

	public List<Authorities> saveAuthorities(List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = addAuthorityRequestList.stream()
			.map(AddAuthorityRequest::toEntity)
			.toList();
		return authoritiesRepository.saveAll(authorities);
	}
}
