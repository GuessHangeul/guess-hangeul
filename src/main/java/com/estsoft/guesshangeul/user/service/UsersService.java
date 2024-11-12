package com.estsoft.guesshangeul.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private final PasswordResetTokenRepository tokenRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final EmailService emailService;

	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

	private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[가-힣a-zA-Z0-9._-]{2,20}$");

	@Value("${app.url}")
	private String appUrl;

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

	// 계정 조회
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

	// 회원 탈퇴 (admin)
	@Transactional
	public boolean withdrawal(Long userId) {
		Users user = usersRepository.findById(userId).orElse(new Users());
		if (user.getId() >= 1) {
			user.withdrawal(true);
			return true;
		}
		return false;
	}

	// 셀프 회원 탈퇴
	@Transactional
	public boolean selfWithdrawal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = (Users)authentication.getPrincipal();
		Users managedUser = usersRepository.findById(user.getId()).orElse(new Users());
		if (managedUser.getId() >= 1) {
			managedUser.withdrawal(true);
			return true;
		}
		return false;
	}

	// 비밀번호 변경 메일
	@Transactional
	public void createTokenSendEmail(Users user) {
		// 기존 토큰이 있다면 삭제
		tokenRepository.deleteByUser(user);

		String token = UUID.randomUUID().toString();
		PasswordResetToken myToken = new PasswordResetToken(user, token);
		tokenRepository.save(myToken);

		String resetUrl = appUrl + "/resetPassword/" + token;
		emailService.sendPasswordResetEmail(user.getEmail(), resetUrl);
	}

	// 토큰 확인
	@Transactional
	public String validatePasswordResetToken(String token) {
		Optional<PasswordResetToken> passwordResetToken = tokenRepository.findByToken(token);

		if (passwordResetToken.isEmpty()) {
			return "invalid";
		}

		if (passwordResetToken.get().isExpired()) {
			tokenRepository.delete(passwordResetToken.get());
			return "expired";
		}

		return "valid";
	}

	// 비밀번호 변경
	@Transactional
	public void changePassword(String token, String newPassword) {
		PasswordResetToken passwordResetToken = tokenRepository.findByToken(token)
			.orElseThrow(() -> new IllegalArgumentException("Invalid token"));

		Users user = passwordResetToken.getUser();
		user.passwordChange(bCryptPasswordEncoder.encode(newPassword));
		tokenRepository.delete(passwordResetToken);
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

	public UsersResponse getUserResponse(Long userId) {
		// userId로 Users 엔티티 조회
		Users users = usersRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("User not found"));

		// userId로 권한 목록 조회
		List<GrantedAuthority> grantedAuthorities = loadUserAuthorities(userId);

		// GrantedAuthority 리스트를 콤마로 구분된 문자열로 변환
		String authorityString = grantedAuthorities.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(", "));

		// UsersResponse 객체 생성 후 반환
		return new UsersResponse(users, authorityString);
	}

	// userId로 권한 조회 메서드 (사용자 권한 목록을 GrantedAuthority로 변환)
	public ArrayList<GrantedAuthority> loadUserAuthorities(Long userId) {
		List<Authorities> authorities = authoritiesRepository.findByUserId(userId);
		ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (Authorities auth : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
		}

		return grantedAuthorities;
	}
	//유저 삭제 메서드(소프트 삭제)
	// public Users deleteUser(Long id, DeleteUsersRequest request){
	// 	Users users = usersRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User not found" + id));
	// 	users.DeleteUsers(request.getUserId(), true);
	// 	return users;
	// }

	// 유저 삭제
	public Users deleteUserById(Long userId) {
		Users users = usersRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		users.setDeleted(true);
		usersRepository.save(users);
		return users;
	}
}

