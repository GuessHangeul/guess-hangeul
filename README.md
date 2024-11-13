# 🐧 ㄴㄹㅁㅆㅁ (초성 퀴즈 커뮤니티)

한글 초성 퀴즈를 서로 공유 할 수 있는 커뮤니티 플랫폼입니다.

### 프로젝트 소개

사용자들이 주제 별로 초성 퀴즈를 직접 만들고, 다른 유저들이 문제를 풀어가며 즐기는 게임 커뮤니티 사이트입니다. <br>
초성으로만 이루어진 단어나 문장을 맞추는 과정에서 언어 감각을 키울 수 있는 공간을 제공합니다.

<br>

#### 👀 주요기능

* 주제 별 초성 퀴즈 생성
* 퀴즈 정답 시 점수 획득 또는 차감
* 점수에 따른 랭킹 시스템
* 커뮤니티 기능

<br>

#### 서비스 링크

* http://15.164.187.253:8080/

<br>

## ⏲️ 개발 기간

* 2024년 10월 28일 ~ 11월 13일
  ![image](/img/일정.png)

<br>

## 💻 개발환경

- IDE : IntelliJ
- Frontend : Bootstrap, JavaScript
- Language : Java 17
- Framework : SpringBoot 3.3.5
- Database : MySQL
- ORM : JPA
- 배포 환경 : AWS EC2, RDS
- 협업 도구 : Notion, GitHub, ERD Cloud, Figma

<br>

## ⭐ 기능 정의서 (요구사항 명세)

![image](img/기능 정의서.png)

<br>

## 💫 화면 설계서

[피그마 바로 가기](https://www.figma.com/design/ZRuXkEUZXM4pKcIEkUEWvE/%E3%84%B4%E3%84%B9%E3%85%81%E3%85%86%E3%85%81?node-id=0-1&node-type=canvas&t=GrF1XmBjmmiEyZX8-0)

| 홈페이지 - 일반 권한          | 홈페이지 - 관리자               |
|-----------------------|--------------------------|
| ![image](img/홈일반.png) | ![image](img/관리자 권한.png) |

| 일반 게시판                   | 문제 게시판                   |
|--------------------------|--------------------------|
| ![image](img/일반 게시판.png) | ![image](img/문제 게시판.png) |

| 게시판 생성                   | 랭킹                   |
|--------------------------|----------------------|
| ![image](img/게시판 생성.png) | ![image](img/랭킹.png) |

| 일반 게시글                  | 문제 게시글                  |
|-------------------------|-------------------------|
| ![image](img/일반게시글.png) | ![image](img/문제게시글.png) |

| 게시글 추가 - 일반               | 게시글 추가 - 문제    |
|---------------------------|----------------|
| ![image](img/일반게시글작성.png) | ![image](img/) |

| 게시글 수정 - 일반               | 게시글 수정 - 문제    |
|---------------------------|----------------|
| ![image](img/일반게시글수정.png) | ![image](img/) |

| 회원가입                   | 로그인                   |
|------------------------|-----------------------|
| ![image](img/회원가입.png) | ![image](img/로그인.png) |

| 계정 찾기                   | 비밀번호 변경                   |
|-------------------------|---------------------------|
| ![image](img/계정 찾기.png) | ![image](img/비밀번호 변경.png) |

| 관리자 - 유저 관리             | 관리자 - 집현전 신청 관리          |
|-------------------------|--------------------------|
| ![image](img/관리자유저.png) | ![image](img/관리자집현전.png) |

| 관리자 - 일반 게시판 관리         | 관리자 - 문제 게시판 관리         |
|-------------------------|-------------------------|
| ![image](img/관리자일반.png) | ![image](img/관리자퀴즈.png) |

| 집현전 신청                  |
|-------------------------|
| ![image](img/집현전신청.png) |

<br>

## ⚙️ ERD 설계도

![ERD](img/ERD.png)

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
| resetNickname                | PUT     | /api/admin/initializeNickname/{user_id}                       | 닉네임 초기화       |
| quizChangeVisibilityHide     | PUT     | /api/admin/quizBoard/{boardId}/post/changeVisibilityHide      | 문제 게시글 숨기기    |
| changeVisibilityHide         | PUT     | /api/admin/generalBoard/{boardId}/post/changeVisibilityHide   | 일반 게시글 숨기기    |
| quizChangeVisibilityUnhidden | PUT     | /api/admin/quizBoard/{boardId}/post/changeVisibilityUnhide    | 문제 게시글 숨기기 해제 |
| changeVisibilityUnhidden     | PUT     | /api/admin/generalBoard/{boardId}/post/changeVisibilityUnhide | 일반 게시글 숨기기 해제 |
| deleteQuizBoard              | PUT     | /api/admin/deleteBoard/quizBoard/{board_id}                   | 문제 게시판 삭제     |
| deleteGeneralBoard           | PUT     | /api/admin/deleteBoard/generalBoard/{board_id}                | 일반 게시판 삭제     |
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
- **박희주**: 게시판 삭제, 게시글 숨기기, 닉네임 초기화, 집현전 신청 승인/거부 API 개발
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