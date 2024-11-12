# 🐧 Spring Demo Project

Spring Demo Project가 어떤 서비스인지 요약 한 줄

### 프로젝트 소개

초코송이가 축구공을 차다가 마법의 양탄자를 타고 날아갔는데, 구름 속에서 자라나는 대파가 말하길, "오늘 저녁엔 고래가 노래하는 콘서트가 열립니다!" 하자, 무지개는 기지개를 펴고 춤추기 시작했어요. 그때 갑자기
햄버거에서 나온 연어가 기타를 들고 나타나서는, "오늘은 핫도그가 없으니 피클이 주인공이야!"라고 외쳤죠.

그러자 반대편에 있던 아이스크림이 녹기 전에 초코칩 친구들을 소환해 파티를 벌였고, 달빛 속에서 회전목마가 돌아가며, "인생은 한 번 뿐이야!"라고 외쳤답니다. 마지막으로, 물방울이 "모두 행복하라!"고 말하며
둥글게 모여 바다를 이루었어요.

이 모든 건 사실 어젯밤에 내가 꿈꿨던 이야기라구요...! 💫 -출처: ChatGPT-

<br>

#### 서비스 링크

* https://chatgpt.com/

<br>

## ⏲️ 개발 기간(혹은 일정 칸반보드)

* 2024년 X월 x일 ~ X월 x일

<br>

## 💻 개발환경

- IDE : IntelliJ
- Version : Java XX
- Framework : SpringBoot X.x.x
- Database : Oracle
- ORM : MyBatis

<br>

## ⭐ 기능 정의서 (요구사항 명세)

<텍스트 혹은 이미지>


<br>

## 💫 화면 설계서

<피그마 첨부 가능>

<br>

## ⚙️ ERD 설계도

![image](https://github.com/user-attachments/assets/6fb54335-7ca3-495b-8a49-297b870a93a7)
출처: ERD 샘플 (https://www.erdcloud.com/d/A8SPzXyvcxr6WAZBv)

<br>

## 🎈API 명세서

### 📁 Member

| 🏷NAME               | ⚙METHOD | 📎URL                             | 📖DESCRIPTION  |
|----------------------|---------|-----------------------------------|----------------|
| signup               | POST    | /api/signup                       | 회원 가입          |
| withdrawal           | PUT     | /api/withDrawal/{userId}          | 회원 탈퇴          |
| selfWithdrawal       | PUT     | /api/selfWithdrawal               | 셀프 회원 탈퇴       |
| checkEmailExists     | POST    | /api/checkEmailDuplicate          | 이메일 중복 체크      |
| checkNicknameExists  | POST    | /api/checkNicknameDuplicate       | 닉네임 중복 체크      |
| login                | POST    | /api/login                        | 로그인            |
| logout               | GET     | /api/logout                       | 로그아웃           |
| createTokenSendEmail | POST    | /api/resetPasswordRequest/{email} | 비밀번호 변경 이메일 전송 |
| resetPassword        | POST    | /api/resetPassword                | 비밀번호 변경        |

### 📁 Board

| 🏷NAME           | ⚙METHOD | 📎URL             | 📖DESCRIPTION |
|------------------|---------|-------------------|---------------|
| getAllBoards     | GET     | /api/board        | 전체 게시판 조회     |
| getQuizBoards    | GET     | /api/quizBoard    | 문제 게시판 목록 조회  |
| getGeneralBoards | GET     | /api/generalBoard | 일반 게시판 목록 조회  |
| createQuizBoard  | POST    | /api/quizBoard    | 문제 게시판 생성     |

### 📁 Post

| 🏷NAME             | ⚙METHOD | 📎URL                                                             | 📖DESCRIPTION     |
|--------------------|---------|-------------------------------------------------------------------|-------------------|
| getAllQuizPosts    | GET     | /api/quizBoard/{board_id}/quizPost?search=제목&isHidden=false       | 문제 게시글 목록 조회 (검색) |
| getAllGeneralPosts | GET     | /api/generalBoard/{board_id}/generalPost?search=제목&isHidden=false | 일반 게시글 목록 조회 (검색) |
| getQuizPostById    | GET     | /api/quizBoard/{board_id}/quizPost/{post_id}                      | 문제 게시글 조회         |
| getGeneralPostById | GET     | /api/generalBoard/{board_id}/generalPost/{post_id}                | 일반 게시글 조회         |
| createQuizPost     | POST    | /api/quizBoard/{board_id}/quizPost                                | 문제 게시글 작성         |
| createGeneralPost  | POST    | /api/generalBoard/{board_id}/generalPost                          | 일반 게시글 작성         |
| updateQuizPost     | PUT     | /api/quizBoard/{board_id}/quizPost/{post_id}                      | 문제 게시글 수정         |
| updateGeneralPost  | PUT     | /api/generalBoard/{board_id}/generalPost/{post_id}                | 일반 게시글 수정         |
| deleteQuizPost     | DELETE  | /api/quizBoard/{board_id}/quizPost/{post_id}                      | 문제 게시글 삭제         |
| deleteGeneralPost  | DELETE  | /api/generalBoard/{board_id}/generalPost/{post_id}                | 일반 게시글 삭제         |

### 📁 Answer

| 🏷NAME       | ⚙METHOD | 📎URL                   | 📖DESCRIPTION |
|--------------|---------|-------------------------|---------------|
| submitAnswer | POST    | /api/checkAnswer        | 정답 제출         |
| revealAnswer | POST    | /api/checkCorrectAnswer | 정답 공개         |

### 📁 Comment

| 🏷NAME        | ⚙METHOD | 📎URL                                                                   | 📖DESCRIPTION   |
|---------------|---------|-------------------------------------------------------------------------|-----------------|
| getComments   | GET     | /api/quizBoard/{board_id}/quizPost/{post_id}/comment                    | 문제 게시판 댓글 목록 보기 |
| getComments   | GET     | /api/generalBoard/{board_id}/generalPost/{post_id}/comment              | 일반 게시판 댓글 목록 보기 |
| addComment    | POST    | /api/quizBoard/{board_id}/quizPost/{post_id}/comment                    | 문제 게시판 댓글 작성    |
| addComment    | POST    | /api/generalBoard/{board_id}/generalPost/{post_id}/comment              | 일반 게시판 댓글 작성    |
| modifyComment | PUT     | /api/quizBoard/{board_id}/quizPost/{post_id}/comment/{comment_id}       | 문제 게시판 댓글 수정    |
| modifyComment | PUT     | /api/generalBoard/{board_id}/generalPost/{post_id}/comment/{comment_id} | 일반 게시판 댓글 수정    |
| deleteComment | DELETE  | /api/quizBoard/{board_id}/quizPost/{post_id}/comment/{comment_id}       | 문제 게시판 댓글 삭제    |
| deleteComment | DELETE  | /api/generalBoard/{board_id}/generalPost/{post_id}/comment/{comment_id} | 일반 게시판 댓글 삭제    |

### 📁 User

| 🏷NAME          | ⚙METHOD | 📎URL                            | 📖DESCRIPTION |
|-----------------|---------|----------------------------------|---------------|
| getAllUsers     | GET     | /api/user                        | 유저 목록 조회      |
| getUserInfo     | GET     | /api/user/{user_id}              | 유저 정보 조회      |
| findAuthority   | GET     | /api/user/findAuthority/{userId} | 권한 조회         |
| addAuthority    | POST    | /api/user/authority              | 권한 추가         |
| deleteAuthority | DELETE  | /api/user/authority              | 권한 삭제         |
| getUserRanking  | GET     | /api/user?sort=score             | 유저 랭킹순 조회     |
| deleteUsers     | PUT     | /api/users/{id}                  | 유저 삭제         |

### 📁 Board Manager Application

| 🏷NAME               | ⚙METHOD | 📎URL                  | 📖DESCRIPTION |
|----------------------|---------|------------------------|---------------|
| addUserRankupRequest | POST    | /api/boardManagerApply | 새 집현전 신청      |

### 📁 Admin

| 🏷NAME                       | ⚙METHOD | 📎URL                                                         | 📖DESCRIPTION |
|------------------------------|---------|---------------------------------------------------------------|---------------|
| resetNickname                | GET     | /api/admin/initializeNickname/{user_id}                       | 닉네임 초기화       |
| quizChangeVisibilityHide     | PUT     | /api/admin/quizBoard/{boardId}/post/changeVisibilityHide      | 문제 게시글 숨기기    |
| changeVisibilityHide         | PUT     | /api/admin/generalBoard/{boardId}/post/changeVisibilityHide   | 일반 게시글 숨기기    |
| quizChangeVisibilityUnhidden | PUT     | /api/admin/quizBoard/{boardId}/post/changeVisibilityUnhide    | 문제 게시글 숨기기 해제 |
| changeVisibilityUnhidden     | PUT     | /api/admin/generalBoard/{boardId}/post/changeVisibilityUnhide | 일반 게시글 숨기기 해제 |
| deleteQuizBoard              | GET     | /api/admin/deleteBoard/quizBoard/{board_id}                   | 문제 게시판 삭제     |
| deleteGeneralBoard           | GET     | /api/admin/deleteBoard/generalBoard/{board_id}                | 일반 게시판 삭제     |
| acceptBoardManager           | PUT     | /api/admin/acceptBoardManager                                 | 집현전 신청 승인     |
| rejectBoardManager           | PUT     | /api/admin/rejectBoardManager                                 | 집현전 신청 거부     |

<br>

## 🎞시연 영상

<br>

## 👨‍💻 멤버 구성 및 역할

### 프로젝트 매니저 (PM) : 방원선

- 팀의 전반적인 일정 관리, 회의 주관, 프로젝트 진행 상황 파악 및 조율
- 기획, 주요 의사결정, 문제 해결 및 팀원 간의 원활한 소통을 위한 서포트

### 백엔드 개발자 : 박주형, 박희주, 방원선, 장준혁, 정승원, 정윤호

모든 팀원이 백엔드 개발자로서 각 기능에 맞는 API 설계 및 개발에 기여하였으며, 다음과 같은 주요 기능을 담당하여 개발했습니다.

데이터 모델링 및 보안 설계, API 최적화, 서버 성능 개선 작업을 공동으로 수행하여 백엔드 안정성을 높였습니다.

- **박주형**: 게시글 작성, 수정, 삭제 및 조회 API 개발
- **박희주**: 게시판 삭제, 게시글 숨기기, 닉네임 초기화 API 개발
- **방원선**: 게시판 생성, 게시판 조회 API 개발
- **장준혁**: 사용자 인증 및 권한 부여, 비밀번호 변경, 회원 관리 API 설계 및 개발
- **정승원**: 유저 정보 조회, 집현전 관리 기능 API 개발
- **정윤호**: 댓글 작성, 수정, 삭제 및 조회 API 개발, 퀴즈 정답 확인 API 개발

### 프론트엔드 개발 및 디자인

모든 팀원이 협력하여 Thymeleaf를 활용한 사용자 인터페이스 구현 및 디자인 요소 적용
화면 구성, UI 개선 작업 수행

### QA 및 테스트

모든 팀원이 QA 역할을 수행하여 테스트 케이스 작성, 오류 및 버그 탐지를 진행하여 품질 검수 및 보고

<br>

## 🛠 코딩 컨벤션

- Java Coding Convention
- 참고 https://www.notion.so/oreumi/202d0d0895884dd7847673fe7d40a0e0
