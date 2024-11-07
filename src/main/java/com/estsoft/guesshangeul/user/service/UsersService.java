package com.estsoft.guesshangeul.user.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.exception.InvalidEmailFormatException;
import com.estsoft.guesshangeul.exception.InvalidNicknameFormatException;
import com.estsoft.guesshangeul.exception.UsersEmailDuplicateException;
import com.estsoft.guesshangeul.exception.UsersNicknameDuplicateException;
import com.estsoft.guesshangeul.exception.UsersNotFoundException;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.dto.UsersResponse;
import com.estsoft.guesshangeul.user.entity.Authorities;
import com.estsoft.guesshangeul.user.entity.PasswordResetToken;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.AuthoritiesRepository;
import com.estsoft.guesshangeul.user.repository.PasswordResetTokenRepository;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
	private final UsersRepository usersRepository;
	private final AuthoritiesRepository authoritiesRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final EmailService emailService;
	private final PasswordResetTokenRepository tokenRepository;
	@Autowired
	private AuthoritiesRepository authoritiesRepository;//UsersRespponse에서 authority 엔티티 접근 위한 추가

	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
	);

	private static final Pattern NICKNAME_PATTERN = Pattern.compile(
		"^[가-힣a-zA-Z0-9._-]{2,20}$"
	);

	@Value("${app.url}")
	private String appUrl;

	public void checkEmailPattern(String email) {
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			throw new InvalidEmailFormatException(email);
		}
	}

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
