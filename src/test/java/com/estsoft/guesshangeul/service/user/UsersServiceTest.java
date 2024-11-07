package com.estsoft.guesshangeul.service.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.exception.InvalidEmailFormatException;
import com.estsoft.guesshangeul.exception.InvalidNicknameFormatException;
import com.estsoft.guesshangeul.exception.UsersEmailDuplicateException;
import com.estsoft.guesshangeul.exception.UsersNicknameDuplicateException;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;
import com.estsoft.guesshangeul.user.service.UsersService;

@SpringBootTest
@Transactional
public class UsersServiceTest {
	@Autowired
	private UsersService usersService;
	@Autowired
	private UsersRepository usersRepository;

	@BeforeEach
	void setup() {
		usersRepository.deleteAll();
	}

	@Test
	void testCheckEmailExistsSuccess() {
		// given
		String exists = "exists@email.com";
		String notExists = "not-exists@email.com";

		Users users = new Users(exists, "", "test");
		usersRepository.save(users);

		// when
		Boolean emailExists = usersService.checkEmailExists(exists);
		Boolean emailNotExists = usersService.checkEmailExists(notExists);

		// then
		assertTrue(emailExists);
		assertFalse(emailNotExists);
	}

	@Test
	void testCheckEmailExistsThrowsInvalidEmailFormatException() {
		// given
		List<String> invalidEmails = List.of(
			"", // empty
			"plainaddress", // missing @
			"@missingusername.com", // missing username
			"username@.com", // missing domain name
			"username@com", // missing dot in domain
			"username@domain..com", // double dot in domain
			"username@domain.c" // single character domain
		);

		// when & then
		for (String email : invalidEmails) {
			assertThrows(InvalidEmailFormatException.class, () -> usersService.checkEmailExists(email),
				"Wrong on email: " + email);
		}
	}

	@Test
	void testCheckNicknamePatternSuccess() {
		// given
		List<String> validNickname = List.of(
			"한글닉네임123",
			"english123",
			"20nickname1234567890", // 20 characters
			"한글20글자닉네임_1234567890"
		);

		// when & then
		for (String nickname : validNickname) {
			assertDoesNotThrow(() -> usersService.checkNicknamePattern(nickname));
		}
	}

	@Test
	void testCheckNicknameExistsThrowsInvalidNicknameFormatException() {
		// given
		List<String> invalidNickname = List.of(
			"",
			"ㄱㄴ",
			"123456789012345678901", // over 20 characters
			"공 백"
		);

		// when & then
		for (String nickname : invalidNickname) {
			assertThrows(InvalidNicknameFormatException.class, () -> usersService.checkNicknameExists(nickname),
				"Wrong on nickname: " + nickname);
		}
	}

	@Test
	void testCheckNicknameExistsSuccess() {
		// given
		String exists = "exist-user";
		String notExists = "not-exists";

		Users users = new Users("user@example.com", "", exists);
		usersRepository.save(users);

		// when
		Boolean nicknameExists = usersService.checkNicknameExists(exists);
		Boolean nicknameNotExists = usersService.checkNicknameExists(notExists);

		// then
		assertTrue(nicknameExists);
		assertFalse(nicknameNotExists);
	}

	@Test
	void testSaveThrowsDuplicateException() {
		// given
		String password = "123";
		List<Users> usersList = List.of(
			new Users("user@example.com", password, "name"),
			new Users("other@email.com", password, "exist-user")
		);
		usersRepository.saveAll(usersList);

		// when & then
		Users user1 = usersList.get(0);
		String duplicatedEmail = user1.getEmail();
		AddUserRequest request1 = new AddUserRequest(duplicatedEmail, password, "other-name");

		request1.setEmail(duplicatedEmail);
		assertThrows(UsersEmailDuplicateException.class, () -> usersService.save(request1),
			"Duplicate on email: " + duplicatedEmail);

		Users user2 = usersList.get(1);
		String duplicatedNickname = user2.getNickname();
		AddUserRequest request2 = new AddUserRequest("new@email.com", password, duplicatedNickname);
		request2.setNickname(duplicatedNickname);
		assertThrows(UsersNicknameDuplicateException.class, () -> usersService.save(request2),
			"Duplicate on nickname: " + duplicatedNickname);
	}
}
