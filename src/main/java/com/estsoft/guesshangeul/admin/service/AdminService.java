package com.estsoft.guesshangeul.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.entity.Users;

@Service
public class AdminService {
	@Autowired
	com.estsoft.guesshangeul.user.repository.usersRepository usersRepository;

	public Users resetNickname(Long userId) {
		Users users = usersRepository.findById(userId).orElseThrow();
		users.setNickname("hangeul_" + users.getId());
		return usersRepository.save(users);
	}

}
