package com.estsoft.guesshangeul.user.dto;

public class RoleType {
	public static final String NOBI = "ROLE_NOBI";
	public static final String PYEONGMIN = "ROLE_PYEONGMIN";
	public static final String YANGBAN = "ROLE_YANGBAN";
	public static final String JIPHYEONJEON = "ROLE_JIPHYEONJEON";
	public static final String KINGSEJONG = "ROLE_KINGSEJONG";

	public static String toName(String role) {
		switch (role) {
			case NOBI:
				return "노비";
			case PYEONGMIN:
				return "평민";
			case YANGBAN:
				return "양반";
			case JIPHYEONJEON:
				return "집현전";
			case KINGSEJONG:
				return "세종대왕";
			default:
				return "알 수 없음";
		}
	}
}
