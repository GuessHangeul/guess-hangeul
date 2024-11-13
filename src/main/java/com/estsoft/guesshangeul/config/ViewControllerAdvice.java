package com.estsoft.guesshangeul.config;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.estsoft.guesshangeul.admin.service.AdminBoardService;
import com.estsoft.guesshangeul.user.dto.RoleType;
import com.estsoft.guesshangeul.user.dto.UsersResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class ViewControllerAdvice {
	private final AdminBoardService adminBoardService;

	// 헤더 사용하는 페이지 URL 정규표현식
	private final String[] usingHeaderRegex = {
		"^/$",
		"^/rank.*$",
		"^/createBoard.*$",
		"^/quizBoard.*$",
		"^/generalBoard.*$",
		"^/newBoardManagerApply.*$",
		"^/admin.*$"
	};

	// 랭킹 데이터 사용하는 페이지 URL 정규표현식
	private final String[] usingRankingRegex = {
		"^/$",
		"^/rank.*$",
		"^/createBoard.*$",
		"^/quizBoard.*$",
		"^/generalBoard.*$"
	};

	@ModelAttribute("roleName")
	public String convertRoleName(HttpServletRequest request, Authentication authentication) {
		String uri = request.getRequestURI();
		if (Arrays.stream(usingHeaderRegex).anyMatch(x -> Pattern.compile(x).matcher(uri).matches())) {
			// 등급 이름 변환하여 주입
			return getRoleName(authentication);
		}
		return null;
	}

	@ModelAttribute("rankingData")
	public List<UsersResponse> insertRankingData(HttpServletRequest request) {
		// URL 매칭되는 ModelAttribute로 랭킹 데이터 입력
		String uri = request.getRequestURI();
		if (Arrays.stream(usingRankingRegex).anyMatch(x -> Pattern.compile(x).matcher(uri).matches())) {
			// 랭킹 데이터 주입
			// List<String> mockRankingData = List.of("세종", "2등", "3등", "4등");
			Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "score"));
			return adminBoardService.findAllUsersbyIsDeleted(false, pageable);
		}
		return List.of();
	}

	private String getRoleName(Authentication authentication) {
		if (authentication == null) {
			return "비회원";
		}

		return authentication.getAuthorities().stream()
			.findFirst()
			.map(GrantedAuthority::getAuthority)
			.map(RoleType::toName)
			.orElse("비회원");
	}
}
