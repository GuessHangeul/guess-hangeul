package com.estsoft.guesshangeul.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.dto.UsersResponse;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminBoardService {
	private final UsersRepository usersRepository;

	public List<UsersResponse> findAllUsersbyIsDeleted(Boolean isDeleted, Pageable pageable) {
		List<Users> usersList = usersRepository.findAllByIsDeleted(isDeleted, pageable);
		return usersList.stream().map(UsersResponse::new).toList();
	}
}
