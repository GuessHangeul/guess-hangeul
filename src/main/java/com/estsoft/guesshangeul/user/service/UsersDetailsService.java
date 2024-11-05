package com.estsoft.guesshangeul.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estsoft.guesshangeul.user.entity.Authorities;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.AuthoritiesRepository;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersDetailsService implements UserDetailsService {
	private final UsersRepository usersRepository;
	private final AuthoritiesRepository authoritiesRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException(username));
		users.setGrantedAuthority(loadUserAuthorities(users.getId()));
		return users;
	}

	public ArrayList<GrantedAuthority> loadUserAuthorities(Long userId) {
		List<Authorities> authorities = authoritiesRepository.findByUserId(userId);
		ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (Authorities auth : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
		}

		return grantedAuthorities;
	}

}
