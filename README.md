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

### Java Coding Convention

[자바 코딩 컨벤션](https://www.notion.so/oreumi/202d0d0895884dd7847673fe7d40a0e0)

### 브랜치

- master: 운영 브랜치
- develop: 개발 브랜치
- feature/이슈번호-기능요약: 기능 추가 브랜치
- feature 브랜치에서 개발 -> develop으로 병합 -> 테스트 후 master로 병합

---

### 커밋 컨벤션

```
type : subject

body

footer
```

```
Feat: "회원 가입 기능 구현"

SMS, 이메일 중복확인 API 개발

Resolves: #123
Ref: #456
Related to: #48, #45
```

### type

| 타입           | 설명                            |
|--------------|-------------------------------|
| **feat**     | 새로운 기능 추가                     |
| **fix**      | 버그 수정                         |
| **docs**     | 문서 수정                         |
| **style**    | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| **refactor** | 코드 리펙토링                       |
| **test**     | 테스트 코드, 리펙토링 테스트 코드 추가        |
| **chore**    | 빌드 업무 수정, 패키지 매니저 수정          |

### body

- 본문은 한 줄 당 72자 내로 작성한다.
- 본문 내용은 양에 구애받지 않고 최대한 상세히 작성한다.
- 본문 내용은 어떻게 변경했는지 보다 무엇을 변경했는지 또는 왜 변경했는지를 설명한다.

### footer

`유형: #이슈번호`

| Fixes      | 이슈 수정중 (아직 해결되지 않은 경우)          |
|------------|---------------------------------|
| Resolves   | 이슈를 해결했을 때 사용                   |
| Ref        | 참고할 이슈가 있을 때 사용                 |
| Related to | 해당 커밋에 관련된 이슈번호 (아직 해결되지 않은 경우) |

---

### 기능 요청 이슈 템플릿

```
## 기능 설명

> 추가하려는 기능에 대해 간결하게 설명해주세요

## 작업 상세 내용

- [ ] TODO
- [ ] TODO
- [ ] TODO

## 참고할만한 자료(선택)
```

### 버그 리포트 이슈 템플릿

```
## 버그 설명

> 어떤 버그인지 간결하게 설명해주세요

## 상황

> (가능하면) Given-When-Then 형식으로 서술해주세요

## 예상 결과

> 예상했던 정상적인 결과가 어떤 것이었는지 설명해주세요

## 참고할만한 자료(선택)

```

### PR 템플릿

```
## #️⃣연관된 이슈

> ex) #이슈번호, #이슈번호

## 📝작업 내용

> 이번 PR에서 작업한 내용을 간략히 설명해주세요(이미지 첨부 가능)

### 스크린샷 (선택)

## 💬리뷰 요구사항(선택)

> 리뷰어가 특별히 봐주었으면 하는 부분이 있다면 작성해주세요
>
> ex) 메서드 Xxx()의 이름을 더 잘 짓고 싶은데 혹시 좋은 명칭이 있을까요?
```