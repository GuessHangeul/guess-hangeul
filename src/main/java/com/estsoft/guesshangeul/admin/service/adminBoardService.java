package com.estsoft.guesshangeul.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.DTO.Users;
import com.estsoft.guesshangeul.user.DTO.UsersResponse;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class adminBoardService {
	private final UsersRepository usersRepository;

	public List<UsersResponse> findAllUsersbyIsDeleted(Boolean isDeleted){
		List<Users> usersList = usersRepository.findAllByIsDeleted(isDeleted);
		return usersList.stream().map(UsersResponse::new).toList();
	}
}
