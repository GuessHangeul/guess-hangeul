package com.estsoft.guesshangeul.util;

import java.io.UnsupportedEncodingException;

public class KoreanInitialExtractor {
	private static final char[] CHO_SUNG = {
		'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
	};

	// 한글 단어 -> 한글 초성 변환
	public static String extractInitials(String input) {
		StringBuilder initials = new StringBuilder();

		for (char c : input.toCharArray()) {
			if (c >= 0xAC00 && c <= 0xD7A3) {
				int unicode = c - 0xAC00;
				int choSungIndex = unicode / (21 * 28);
				initials.append(CHO_SUNG[choSungIndex]);
			} else {
				// 한글이 아닌 경우 그대로 추가
				initials.append(c);
			}
		}

		return initials.toString();
	}

	// 테스트
	public static void main(String[] args) throws UnsupportedEncodingException {
		String testString = "안녕하세요";
		String actual = extractInitials(testString);
		String expected = "ㅇㄴㅎㅅㅇ";
		System.out.println(actual.equals(expected));
	}
}
