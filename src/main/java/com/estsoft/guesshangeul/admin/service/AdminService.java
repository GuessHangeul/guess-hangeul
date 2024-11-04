package com.estsoft.guesshangeul.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

@Service
public class AdminService {
	@Autowired
	UsersRepository usersRepository;

	public Users resetNickname(Long user_id) {
		Users users = usersRepository.findById(user_id).orElseThrow();
		users.setNickname("hangeul_" + users.getId());
		return usersRepository.save(users);
	}

}
