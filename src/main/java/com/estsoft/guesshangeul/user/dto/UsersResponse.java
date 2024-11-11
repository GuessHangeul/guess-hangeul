package com.estsoft.guesshangeul.user.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.user.entity.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponse {
	private Long userId;
	private String nickname;
	private String authority;
	private String createdAt;
	private String connectedAt;
	private int connectCount;
	private boolean isDeleted;

	public UsersResponse(Users users) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		this.createdAt = users.getCreatedAt().format(formatter);
		this.connectedAt = users.getConnectedAt().format(formatter);
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}

	public UsersResponse(Users users, String authorityString) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		String FixAuthority = "";
		switch (authorityString) {
			case "ROLE_NOBI":
				FixAuthority = "노비";
				break;
			case "ROLE_PYEONGMIN":
				FixAuthority = "평민";
				break;
			case "ROLE_YANGBAN":
				FixAuthority = "양반";
				break;
			case "ROLE_JIPHYEONJEON":
				FixAuthority = "집현전";
				break;
			case "ROLE_KINGSEJONG":
				FixAuthority = "세종대왕";
				break;
		}
		this.authority = FixAuthority;
		this.createdAt = users.getCreatedAt().format(formatter);
		this.connectedAt = users.getConnectedAt().format(formatter);
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}
}
