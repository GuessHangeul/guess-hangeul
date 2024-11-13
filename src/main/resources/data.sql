-- 유저 예시
INSERT INTO `users`
(`user_id`, `connect_count`, `connected_at`, `created_at`, `email`, `is_deleted`, `nickname`, `password`, `score`)
VALUES
(1, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'juhyeong@root.com', 0, '박주형', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(2, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'huiju@root.com', 0, '박희주', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(3, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'wonsun@root.com', 0, '방원선', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(4, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'junhyeok@root.com', 0, '장준혁', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(5, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'seungwon@root.com', 0, '정승원', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(6, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'yunho@root.com', 0, '정윤호', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(7, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'user1@user.com', 0, '유저명1', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(8, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'user2@user.com', 0, '유저명2', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(9, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'user3@user.com', 0, '유저명3', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0),
(10, 0, '2024-11-12 09:00:00', '2024-11-12 09:00:00', 'user4@user.com', 0, '유저명4', '$2a$10$JM9O1jZ4H03O0QC4ndhF7.GOsvZ2RKpcM/rkVStwbVkkzlBxHanLi', 0)
;

-- 권한 예시
INSERT INTO `authorities`
(`id`, `user_id`, `authority`)
VALUES
(1, 1, 'ROLE_KINGSEJONG'),
(2, 2, 'ROLE_KINGSEJONG'),
(3, 3, 'ROLE_KINGSEJONG'),
(4, 4, 'ROLE_KINGSEJONG'),
(5, 5, 'ROLE_KINGSEJONG'),
(6, 6, 'ROLE_KINGSEJONG'),
(7, 7, 'ROLE_JIPHYEONJEON'),
(8, 8, 'ROLE_YANGBAN'),
(9, 9, 'ROLE_PYEONGMIN'),
(10, 10, 'ROLE_NOBI')
;


-- 일반 게시판 예시
INSERT INTO `general_board`
(`general_board_id`, `created_at`, `is_deleted`, `title`)
VALUES
(1, '2024-11-12 09:00:00', 0, '잡담 게시판'),
(2, '2024-11-12 09:00:00', 0, 'Q&A'),
(3, '2024-11-12 09:00:00', 0, '팁 게시판')
;

-- 잡담 게시판 게시글 예시
INSERT INTO `general_post`
(`general_post_id`, `content`, `created_at`, `is_hidden`, `title`, `updated_at`, `view`, `general_board_id`, `user_id`)
VALUES
(1, '오늘 날씨 정말 좋네요. 다들 뭐하고 계신가요?', '2024-11-12 10:10:00', 0, '날씨 이야기', '2024-11-12 10:10:00', 15, 1, 2),
(2, '요즘 어떤 책들 읽고 계신가요? 추천 좀 해주세요!', '2024-11-12 10:20:00', 0, '책 추천 부탁드려요', '2024-11-12 10:20:00', 8, 1, 3),
(3, '다들 점심 뭐 드셨나요? 저는 떡볶이 먹었어요.', '2024-11-12 10:30:00', 0, '점심 메뉴 공유해요', '2024-11-12 10:30:00', 12, 1, 5),
(4, '주말에 할 만한 취미 활동 있을까요? 아이디어 좀 주세요!', '2024-11-12 10:40:00', 0, '주말 취미 추천', '2024-11-12 10:40:00', 9, 1, 7),
(5, '아침에 일어나는 게 너무 힘드네요. 다들 어떻게 극복하시나요?', '2024-11-12 10:50:00', 0, '아침에 일어나는 팁', '2024-11-12 10:50:00', 11, 1, 9);

-- Q&A 게시판 게시글 예시 (초성 퀴즈 관련 질문)
INSERT INTO `general_post`
(`general_post_id`, `content`, `created_at`, `is_hidden`, `title`, `updated_at`, `view`, `general_board_id`, `user_id`)
VALUES
(6, '초성 "ㅎㅈㄱ"가 들어가는 단어가 뭐가 있을까요?', '2024-11-12 11:10:00', 0, 'ㅎㅈㄱ로 시작하는 단어', '2024-11-12 11:10:00', 20, 2, 4),
(7, '난이도 높은 초성 퀴즈를 푸는 요령이 있을까요?', '2024-11-12 11:20:00', 0, '고난도 초성 퀴즈 푸는 법', '2024-11-12 11:20:00', 15, 2, 6),
(8, '초성 퀴즈에서 "ㅅㅌㄹㅍ"는 무엇을 의미하나요?', '2024-11-12 11:30:00', 0, 'ㅅㅌㄹㅍ 초성 뜻', '2024-11-12 11:30:00', 18, 2, 8),
(9, '친구들이랑 초성 퀴즈할 때 쓸만한 초성 아이디어 있나요?', '2024-11-12 11:40:00', 0, '재미있는 초성 퀴즈 아이디어', '2024-11-12 11:40:00', 10, 2, 1),
(10, '자주 나오는 초성 퀴즈 단어 추천 좀 해주세요!', '2024-11-12 11:50:00', 0, '자주 나오는 초성 퀴즈 단어', '2024-11-12 11:50:00', 12, 2, 10);

-- 팁 게시판 게시글 예시
INSERT INTO `general_post`
(`general_post_id`, `content`, `created_at`, `is_hidden`, `title`, `updated_at`, `view`, `general_board_id`, `user_id`)
VALUES
(11, '초성 퀴즈는 단어 길이가 긴 것부터 생각하는 게 좋더라고요.', '2024-11-12 12:10:00', 0, '초성 퀴즈 푸는 팁: 긴 단어부터', '2024-11-12 12:10:00', 25, 3, 3),
(12, '자주 나오는 초성 조합 정리해봤어요. 도움 되길 바랍니다!', '2024-11-12 12:20:00', 0, '자주 나오는 초성 조합', '2024-11-12 12:20:00', 14, 3, 2),
(13, '어려운 초성 문제는 뜻을 추측해보면 쉽게 풀리기도 해요.', '2024-11-12 12:30:00', 0, '초성 퀴즈: 뜻을 먼저 생각하기', '2024-11-12 12:30:00', 17, 3, 7),
(14, '초성 퀴즈 내기할 때 난이도 조절하는 방법을 공유합니다.', '2024-11-12 12:40:00', 0, '난이도 조절하는 초성 퀴즈 팁', '2024-11-12 12:40:00', 19, 3, 5),
(15, '초성 퀴즈 앱 추천드립니다! 유용하게 써보세요.', '2024-11-12 12:50:00', 0, '초성 퀴즈에 도움되는 앱 추천', '2024-11-12 12:50:00', 13, 3, 9);

-- 잡담 게시판 댓글 예시
INSERT INTO `general_comment`
(`general_comment_id`, `content`, `created_at`, `general_post_id`, `user_id`)
VALUES
(1, '날씨 정말 좋네요! 오늘은 밖에서 활동하기 좋을 것 같아요.', '2024-11-12 10:12:00', 1, 3),  -- 댓글 1개

(2, '책 추천 받는 거 정말 좋아요! 최근에 어떤 책 읽으셨어요?', '2024-11-12 10:22:00', 2, 4),  -- 댓글 1개

(3, '떡볶이 진짜 맛있죠! 저는 치킨도 먹었어요.', '2024-11-12 10:32:00', 3, 6),  -- 댓글 1개

(4, '주말에 할 만한 취미 너무 궁금하네요. 저도 추천받고 싶어요!', '2024-11-12 10:42:00', 4, 2),  -- 댓글 0개

(5, '아침에 일어나는 게 힘든 건 모두가 겪는 일이죠. 저도 힘들어요.', '2024-11-12 10:52:00', 5, 7),  -- 댓글 2개
(6, '저는 알람을 여러 개 맞춰두고 일어납니다.', '2024-11-12 10:53:00', 5, 5)
;

-- Q&A 게시판 댓글 예시
INSERT INTO `general_comment`
(`general_comment_id`, `content`, `created_at`, `general_post_id`, `user_id`)
VALUES
(7, 'ㅎㅈㄱ로 시작하는 단어는 한자어에서 많이 찾을 수 있을 것 같아요.', '2024-11-12 11:12:00', 6, 2),  -- 댓글 0개

(8, '고난도 퀴즈는 단어가 너무 길면 풀기 어려워요.', '2024-11-12 11:22:00', 7, 3),  -- 댓글 1개

(9, 'ㅅㅌㄹㅍ는 \'스마트폰\'을 의미할 수도 있을 것 같아요.', '2024-11-12 11:32:00', 8, 6),  -- 댓글 2개
(10, '맞아요, 예전에 그런 초성 퀴즈 풀었던 기억이 나요.', '2024-11-12 11:33:00', 8, 8),

(11, '초성 아이디어 정말 유용하네요! 재미있게 풀어보겠습니다.', '2024-11-12 11:42:00', 9, 7),  -- 댓글 0개

(12, '자주 나오는 단어들에 대한 팁 좋네요. 감사합니다.', '2024-11-12 11:52:00', 10, 5)  -- 댓글 1개
;

-- 팁 게시판 댓글 예시
INSERT INTO `general_comment`
(`general_comment_id`, `content`, `created_at`, `general_post_id`, `user_id`)
VALUES
(13, '긴 단어부터 생각하는 게 정말 중요한 팁인 것 같아요.', '2024-11-12 12:12:00', 11, 4),  -- 댓글 1개

(14, '초성 퀴즈는 자주 나오는 초성을 먼저 생각하면 풀기 쉬워요.', '2024-11-12 12:22:00', 12, 2),  -- 댓글 0개

(15, '뜻을 먼저 생각하는 방법 정말 유용하네요. 다음에 시도해보겠습니다.', '2024-11-12 12:32:00', 13, 3),  -- 댓글 2개
(16, '저도 그 방법 써봤는데 효과가 있더라고요.', '2024-11-12 12:33:00', 13, 7),

(17, '난이도 조절하는 방법에 대해 더 알고 싶어요!', '2024-11-12 12:42:00', 14, 5),  -- 댓글 0개

(18, '유용한 앱 추천 감사합니다! 한번 사용해봐야겠네요.', '2024-11-12 12:52:00', 15, 6)  -- 댓글 1개
;



-- 문제 게시판 예시
INSERT INTO `quiz_board`
(`quiz_board_id`, `created_at`, `is_deleted`, `title`, `user_id`)
VALUES
(1, '2024-11-12 09:00:00', 0, '속담', 1),
(2, '2024-11-12 09:00:00', 0, '음악', 2),
(3, '2024-11-12 09:00:00', 0, '영화', 3),
(4, '2024-11-12 09:00:00', 0, '과자', 4),
(5, '2024-11-12 09:00:00', 0, '유튜브', 5),
(6, '2024-11-12 09:00:00', 0, '오르미', 6)
;

-- 속담 게시판의 초성 퀴즈 예시
INSERT INTO `quiz_post`
(`quiz_post_id`, `answer`, `created_at`, `hint_content`, `is_hidden`, `quiz_title`, `updated_at`, `view`, `quiz_board_id`, `user_id`)
VALUES
(1, '가는 말이 고와야 오는 말이 곱다', '2024-11-12 10:10:00', '상대방에게 하는 행동이 돌아온다.', 0, 'ㄱㅁㄱㅇㅇㅇㅁㄱㄷ', '2024-11-12 10:10:00', 30, 1, 1),
(2, '호랑이도 제 말 하면 온다', '2024-11-12 10:20:00', '말하는 대로 나타나는 상황', 0, 'ㅎㄹㅇㄷㅈㅁㅎㅇㄷ', '2024-11-12 10:20:00', 28, 1, 2),
(3, '사공이 많으면 배가 산으로 간다', '2024-11-12 10:30:00', '많은 사람이 관여하면 일이 엉망이 된다.', 0, 'ㅅㄱㅇㅁㅇㅂㄱㅅㅇㄱㄷ', '2024-11-12 10:30:00', 25, 1, 3),
(4, '빈 수레가 요란하다', '2024-11-12 10:40:00', '실속 없는 사람일수록 겉으로 더 떠들다.', 0, 'ㅂㅅㄹㄱㅇㄹㅎㄷ', '2024-11-12 10:40:00', 20, 1, 4),
(5, '고생 끝에 낙이 온다', '2024-11-12 10:50:00', '어려운 시간을 지나면 좋은 결과가 온다.', 0, 'ㄱㅅㄲㅇㄴㅇㅇㄷ', '2024-11-12 10:50:00', 35, 1, 5);

-- 음악 게시판의 초성 퀴즈 예시
INSERT INTO `quiz_post`
(`quiz_post_id`, `answer`, `created_at`, `hint_content`, `is_hidden`, `quiz_title`, `updated_at`, `view`, `quiz_board_id`, `user_id`)
VALUES
(6, '벚꽃 엔딩', '2024-11-12 11:10:00', '봄 하면 떠오르는 노래', 0, 'ㅂㄲㅇㄷ', '2024-11-12 11:10:00', 50, 2, 1),
(7, '좋은 날', '2024-11-12 11:20:00', '아이유의 히트곡, 삼단 고음', 0, 'ㅈㅇㄴ', '2024-11-12 11:20:00', 45, 2, 2),
(8, 'Gangnam Style', '2024-11-12 11:30:00', '싸이의 글로벌 히트곡', 0, 'ㄱㄴㅅㅌㄹ', '2024-11-12 11:30:00', 48, 2, 3),
(9, '다시 여기 바닷가', '2024-11-12 11:40:00', '여름을 주제로 한 노래', 0, 'ㄷㅅㅇㄱㅂㄷㄱ', '2024-11-12 11:40:00', 38, 2, 4),
(10, '아리랑', '2024-11-12 11:50:00', '한국의 전통 민요', 0, 'ㅇㄹㄹ', '2024-11-12 11:50:00', 52, 2, 5);

-- 영화 게시판의 초성 퀴즈 예시
INSERT INTO `quiz_post`
(`quiz_post_id`, `answer`, `created_at`, `hint_content`, `is_hidden`, `quiz_title`, `updated_at`, `view`, `quiz_board_id`, `user_id`)
VALUES
(11, '기생충', '2024-11-12 12:10:00', '봉준호 감독의 영화, 아카데미 수상작', 0, 'ㄱㅅㅊ', '2024-11-12 12:10:00', 60, 3, 3),
(12, '인터스텔라', '2024-11-12 12:20:00', '우주를 배경으로 한 SF 영화', 0, 'ㅇㅌㅅㅌㄹ', '2024-11-12 12:20:00', 54, 3, 4),
(13, '신과 함께', '2024-11-12 12:30:00', '저승 세계를 다룬 한국 영화', 0, 'ㅅㄱㅎㄱ', '2024-11-12 12:30:00', 42, 3, 5),
(14, '타이타닉', '2024-11-12 12:40:00', '대형 유람선의 비극적인 사고를 다룬 영화', 0, 'ㅌㅇㅌㄴ', '2024-11-12 12:40:00', 33, 3, 1),
(15, '어벤져스', '2024-11-12 12:50:00', '마블의 인기 히어로 영화', 0, 'ㅇㅂㅈㅅ', '2024-11-12 12:50:00', 41, 3, 2);

-- 과자 게시판의 초성 퀴즈 예시
INSERT INTO `quiz_post`
(`quiz_post_id`, `answer`, `created_at`, `hint_content`, `is_hidden`, `quiz_title`, `updated_at`, `view`, `quiz_board_id`, `user_id`)
VALUES
(16, '새우깡', '2024-11-12 13:10:00', '바다를 떠올리게 하는 과자', 0, 'ㅅㅇㄲ', '2024-11-12 13:10:00', 40, 4, 3),
(17, '오징어집', '2024-11-12 13:20:00', '짭조름한 맛의 과자', 0, 'ㅇㅈㅇㅈ', '2024-11-12 13:20:00', 39, 4, 4),
(18, '포카칩', '2024-11-12 13:30:00', '얇고 바삭한 감자칩', 0, 'ㅍㅋㅊ', '2024-11-12 13:30:00', 47, 4, 5),
(19, '칙촉', '2024-11-12 13:40:00', '초코칩이 듬뿍 들어간 과자', 0, 'ㅊㅊ', '2024-11-12 13:40:00', 31, 4, 1),
(20, '홈런볼', '2024-11-12 13:50:00', '안에 초코 크림이 들어있는 과자', 0, 'ㅎㄹㅂ', '2024-11-12 13:50:00', 32, 4, 2);

-- 유튜브 게시판의 초성 퀴즈 예시 (요청한 채널로 수정)
INSERT INTO `quiz_post`
(`quiz_post_id`, `answer`, `created_at`, `hint_content`, `is_hidden`, `quiz_title`, `updated_at`, `view`, `quiz_board_id`, `user_id`)
VALUES
(21, 'BLACKPINK', '2024-11-12 14:10:00', '세계적인 K-pop 걸그룹', 0, 'ㅂㄹㅍㄱ', '2024-11-12 14:10:00', 55, 5, 1),
(22, '지무비', '2024-11-12 14:20:00', '영화 리뷰 및 분석 채널', 0, 'ㅈㅁㅂ', '2024-11-12 14:20:00', 43, 5, 2),
(23, 'SPOTV', '2024-11-12 14:30:00', '스포츠 중계 및 분석 채널', 0, 'ㅅㅍㅌㅂ', '2024-11-12 14:30:00', 57, 5, 3),
(24, '침착맨', '2024-11-12 14:40:00', '게임 및 다양한 콘텐츠 방송', 0, 'ㅊㅊㅁ', '2024-11-12 14:40:00', 49, 5, 4),
(25, '워크맨', '2024-11-12 14:50:00', '장성규가 다양한 직업을 체험하는 방송', 0, 'ㅇㅋㅁ', '2024-11-12 14:50:00', 60, 5, 5);

-- 속담 게시판의 초성 퀴즈에 댓글 추가
INSERT INTO `quiz_comment`
(`quiz_comment_id`, `quiz_post_id`, `content`, `created_at`, `user_id`)
VALUES
(1, 1, '맞아요! 정말 그렇네요!', '2024-11-12 10:15:00', 2),  -- 댓글 1개
(2, 1, '이 속담 정말 재밌어요!', '2024-11-12 10:16:00', 3),

(3, 2, '이 말 진짜 맞는 것 같아요!', '2024-11-12 10:25:00', 1),  -- 댓글 1개

(4, 3, '항상 이런 상황에서 고민하죠.', '2024-11-12 10:35:00', 4),  -- 댓글 0개

(5, 4, '빈 수레가 요란하다니 정말 적절한 표현이에요.', '2024-11-12 10:45:00', 5),  -- 댓글 2개
(6, 4, '맞아요! 겉모습만 화려하고 실속 없죠.', '2024-11-12 10:46:00', 6),

(7, 5, '고생 끝에 낙이 온다 정말 멋진 말이에요.', '2024-11-12 10:55:00', 2),  -- 댓글 0개

-- 음악 게시판의 초성 퀴즈에 댓글 추가
(8, 6, '이 노래 너무 좋아요!', '2024-11-12 11:15:00', 3),  -- 댓글 1개

(9, 7, '아이유 너무 사랑해요!', '2024-11-12 11:25:00', 4),  -- 댓글 2개
(10, 7, '삼단 고음 진짜 대단하죠!', '2024-11-12 11:26:00', 5),

(11, 8, '이 노래 정말 대박!', '2024-11-12 11:35:00', 2),  -- 댓글 0개

-- 영화 게시판의 초성 퀴즈에 댓글 추가
(12, 11, '기생충은 정말 강렬한 영화였어요.', '2024-11-12 12:15:00', 1),  -- 댓글 2개
(13, 11, '봉준호 감독의 영화 최고!', '2024-11-12 12:16:00', 2),

(14, 12, '인터스텔라 정말 명작이죠.', '2024-11-12 12:25:00', 3),  -- 댓글 0개

(15, 13, '신과 함께 너무 재밌었어요!', '2024-11-12 12:35:00', 4),  -- 댓글 1개

(16, 14, '타이타닉 진짜 감동적이었어요.', '2024-11-12 12:45:00', 5),  -- 댓글 0개

-- 과자 게시판의 초성 퀴즈에 댓글 추가
(17, 16, '새우깡 최고!', '2024-11-12 13:15:00', 2),  -- 댓글 2개
(18, 16, '바다 맛이 느껴져요.', '2024-11-12 13:16:00', 3),

(19, 17, '오징어집 맛있어요!', '2024-11-12 13:25:00', 4),  -- 댓글 1개

(20, 18, '포카칩 진짜 얇고 맛있어요!', '2024-11-12 13:35:00', 5),  -- 댓글 0개

(21, 19, '칙촉은 언제나 사랑입니다.', '2024-11-12 13:45:00', 1),  -- 댓글 2개
(22, 19, '초코칩이 최고!', '2024-11-12 13:46:00', 2),

-- 유튜브 게시판의 초성 퀴즈에 댓글 추가
(23, 21, 'BLACKPINK 최고!', '2024-11-12 14:15:00', 4),  -- 댓글 1개

(24, 22, '지무비 리뷰 너무 유익해요.', '2024-11-12 14:25:00', 5),  -- 댓글 0개

(25, 23, 'SPOTV 정말 좋아요!', '2024-11-12 14:35:00', 2),  -- 댓글 2개
(26, 23, '스포츠 중계 정말 잘해요.', '2024-11-12 14:36:00', 3);