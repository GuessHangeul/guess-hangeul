package com.estsoft.guesshangeul.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.dto.Users;
import com.estsoft.guesshangeul.user.dto.usersResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class adminBoardService {
	private final com.estsoft.guesshangeul.user.repository.usersRepository usersRepository;

	public List<usersResponse> findAllUsersbyIsDeleted(Boolean isDeleted){
		List<Users> usersList = usersRepository.findAllByIsDeleted(isDeleted);
		return usersList.stream().map(usersResponse::new).toList();
	}
}
