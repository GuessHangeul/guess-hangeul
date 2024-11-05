package com.estsoft.guesshangeul.service.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.exception.InvalidEmailFormatException;
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
			"username@domain.c", // single character domain
			"username@domain.corporate" // too long TLD
		);

		// when & then
		for (String email : invalidEmails) {
			assertThrows(InvalidEmailFormatException.class, () -> usersService.checkEmailExists(email),
				"Wrong on email: " + email);
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
}
