package com.estsoft.guesshangeul.user.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.exception.InvalidEmailFormatException;
import com.estsoft.guesshangeul.exception.UsersNotFoundException;
import com.estsoft.guesshangeul.user.dto.AddAuthorityRequest;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.entity.Authorities;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.AuthoritiesRepository;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersService {
	private final UsersRepository usersRepository;
	private final AuthoritiesRepository authoritiesRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$"
	);

	public void checkEmailPattern(String email) {
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			throw new InvalidEmailFormatException(email);
		}
	}

	public Users findUserByEmail(String email) {
		return usersRepository.findByEmail(email).orElseThrow(() -> new UsersNotFoundException("email", email));
	}

	public Users save(AddUserRequest request) {
		request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		return usersRepository.save(request.toEntity());
	}

	public Boolean checkEmailExists(String email) {
		checkEmailPattern(email);
		Optional<Users> result = usersRepository.findByEmail(email);
		return result.isPresent();
	}

	public List<Authorities> saveAuthorities(List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = addAuthorityRequestList.stream()
			.map(AddAuthorityRequest::toEntity)
			.toList();
		return authoritiesRepository.saveAll(authorities);
	}
}
