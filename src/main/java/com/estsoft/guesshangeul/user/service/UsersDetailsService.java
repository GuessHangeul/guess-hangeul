package com.estsoft.guesshangeul.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.user.dto.AddAuthorityRequest;
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
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 아이디로 DB 조회후 UserDetails에 담아 반환
		Users users = usersRepository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException(username));
		// userId 로 권한을 가져와 UserDetails에 입력
		users.setGrantedAuthority(loadUserAuthorities(users.getId()));
		return users;
	}

	// 권한 조회
	public ArrayList<GrantedAuthority> loadUserAuthorities(Long userId) {
		List<Authorities> authorities = authoritiesRepository.findByUserId(userId);
		ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (Authorities auth : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
		}

		return grantedAuthorities;
	}

	// 권한 추가
	public List<Authorities> saveUserAuthorities(List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = addAuthorityRequestList.stream()
			.map(AddAuthorityRequest::toEntity)
			.toList();
		return authoritiesRepository.saveAll(authorities);
	}

	// 권한 삭제
	public void deleteUserAuthorities(List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = addAuthorityRequestList.stream()
			.map(AddAuthorityRequest::toEntity)
			.toList();
		authoritiesRepository.deleteAll(authorities);
	}

}
