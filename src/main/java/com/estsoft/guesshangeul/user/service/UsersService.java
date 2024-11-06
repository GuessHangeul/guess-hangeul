package com.estsoft.guesshangeul.user.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.exception.InvalidEmailFormatException;
import com.estsoft.guesshangeul.exception.InvalidNicknameFormatException;
import com.estsoft.guesshangeul.exception.UsersEmailDuplicateException;
import com.estsoft.guesshangeul.exception.UsersNicknameDuplicateException;
import com.estsoft.guesshangeul.exception.UsersNotFoundException;
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

	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
	);

	private static final Pattern NICKNAME_PATTERN = Pattern.compile(
		"^[가-힣a-zA-Z0-9._-]{2,20}$"
	);

	public void checkEmailPattern(String email) {
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			throw new InvalidEmailFormatException(email);
		}
	}

	public void checkNicknamePattern(String nickname) {
		if (!NICKNAME_PATTERN.matcher(nickname).matches()) {
			throw new InvalidNicknameFormatException(nickname);
		}
	}

	public Users findUserByEmail(String email) {
		return usersRepository.findByEmail(email).orElseThrow(() -> new UsersNotFoundException("email", email));
	}

	// 회원 가입
	public Users save(AddUserRequest request) {
		String email = request.getEmail();
		if (checkEmailExists(email)) {
			throw new UsersEmailDuplicateException(email);
		}
		String nickname = request.getNickname();
		if (checkNicknameExists(request.getNickname())) {
			throw new UsersNicknameDuplicateException(nickname);
		}
		request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		return usersRepository.save(request.toEntity());
	}

	public Boolean checkEmailExists(String email) {
		checkEmailPattern(email);
		Optional<Users> result = usersRepository.findByEmail(email);
		return result.isPresent();
	}

	public Boolean checkNicknameExists(String nickname) {
		checkNicknamePattern(nickname);
		Optional<Users> result = usersRepository.findByNickname(nickname);
		return result.isPresent();
	}

	public List<Authorities> saveAuthorities(List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = addAuthorityRequestList.stream()
			.map(AddAuthorityRequest::toEntity)
			.toList();
		return authoritiesRepository.saveAll(authorities);
	}
}
