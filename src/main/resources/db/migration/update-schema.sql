CREATE TABLE authorities
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    user_id   BIGINT                NULL,
    authority VARCHAR(255)          NULL,
    CONSTRAINT pk_authorities PRIMARY KEY (id)
);

CREATE TABLE board_manager_apply
(
    board_manager_apply_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id                BIGINT                NULL,
    status                 INT DEFAULT 0         NOT NULL,
    created_at             datetime              NULL,
    CONSTRAINT pk_board_manager_apply PRIMARY KEY (board_manager_apply_id)
);

CREATE TABLE general_board
(
    general_board_id BIGINT AUTO_INCREMENT NOT NULL,
    title            VARCHAR(255)          NOT NULL,
    created_at       datetime              NULL,
    is_deleted       BIT(1) DEFAULT 0      NOT NULL,
    CONSTRAINT pk_general_board PRIMARY KEY (general_board_id)
);

CREATE TABLE general_comment
(
    general_comment_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id            BIGINT                NULL,
    general_post_id    BIGINT                NULL,
    content            VARCHAR(255)          NOT NULL,
    created_at         datetime              NOT NULL,
    CONSTRAINT pk_general_comment PRIMARY KEY (general_comment_id)
);

CREATE TABLE general_post
(
    general_post_id  BIGINT AUTO_INCREMENT NOT NULL,
    user_id          BIGINT                NULL,
    general_board_id BIGINT                NULL,
    title            VARCHAR(255)          NOT NULL,
    content          VARCHAR(255)          NOT NULL,
    is_hidden        BIT(1) DEFAULT 0      NOT NULL,
    view             BIGINT                NOT NULL,
    created_at       datetime              NULL,
    updated_at       datetime              NULL,
    CONSTRAINT pk_general_post PRIMARY KEY (general_post_id)
);

CREATE TABLE quiz_board
(
    quiz_board_id BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255)          NOT NULL,
    user_id       BIGINT                NOT NULL,
    created_at    datetime              NULL,
    is_deleted    BIT(1) DEFAULT 0      NOT NULL,
    CONSTRAINT pk_quiz_board PRIMARY KEY (quiz_board_id)
);

CREATE TABLE quiz_comment
(
    quiz_comment_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id         BIGINT                NULL,
    quiz_post_id    BIGINT                NULL,
    content         VARCHAR(255)          NOT NULL,
    created_at      datetime              NOT NULL,
    CONSTRAINT pk_quiz_comment PRIMARY KEY (quiz_comment_id)
);

CREATE TABLE quiz_post
(
    quiz_post_id  BIGINT AUTO_INCREMENT NOT NULL,
    user_id       BIGINT                NULL,
    quiz_board_id BIGINT                NULL,
    quiz_title    VARCHAR(255)          NOT NULL,
    hint_content  VARCHAR(255)          NOT NULL,
    answer        VARCHAR(255)          NOT NULL,
    is_hidden     BIT(1) DEFAULT 0      NOT NULL,
    view          BIGINT                NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    CONSTRAINT pk_quiz_post PRIMARY KEY (quiz_post_id)
);

CREATE TABLE users
(
    user_id       BIGINT AUTO_INCREMENT NOT NULL,
    email         VARCHAR(255)          NOT NULL,
    password      VARCHAR(255)          NOT NULL,
    nickname      VARCHAR(255)          NOT NULL,
    score         INT                   NOT NULL,
    created_at    datetime              NOT NULL,
    connected_at  datetime              NULL,
    connect_count INT                   NOT NULL,
    is_deleted    BIT(1)                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE board_manager_apply
    ADD CONSTRAINT FK_BOARD_MANAGER_APPLY_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE general_comment
    ADD CONSTRAINT FK_GENERAL_COMMENT_ON_GENERAL_POST FOREIGN KEY (general_post_id) REFERENCES general_post (general_post_id);

ALTER TABLE general_comment
    ADD CONSTRAINT FK_GENERAL_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE general_post
    ADD CONSTRAINT FK_GENERAL_POST_ON_GENERAL_BOARD FOREIGN KEY (general_board_id) REFERENCES general_board (general_board_id);

ALTER TABLE general_post
    ADD CONSTRAINT FK_GENERAL_POST_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE quiz_comment
    ADD CONSTRAINT FK_QUIZ_COMMENT_ON_QUIZ_POST FOREIGN KEY (quiz_post_id) REFERENCES quiz_post (quiz_post_id);

ALTER TABLE quiz_comment
    ADD CONSTRAINT FK_QUIZ_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE quiz_post
    ADD CONSTRAINT FK_QUIZ_POST_ON_QUIZ_BOARD FOREIGN KEY (quiz_board_id) REFERENCES general_board (general_board_id);

ALTER TABLE quiz_post
    ADD CONSTRAINT FK_QUIZ_POST_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);
CREATE TABLE authorities
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    user_id   BIGINT                NULL,
    authority VARCHAR(255)          NULL,
    CONSTRAINT pk_authorities PRIMARY KEY (id)
);

CREATE TABLE board_manager_apply
(
    board_manager_apply_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id                BIGINT                NULL,
    status                 INT DEFAULT 0         NOT NULL,
    created_at             datetime              NULL,
    CONSTRAINT pk_board_manager_apply PRIMARY KEY (board_manager_apply_id)
);

CREATE TABLE general_board
(
    general_board_id BIGINT AUTO_INCREMENT NOT NULL,
    title            VARCHAR(255)          NOT NULL,
    created_at       datetime              NULL,
    is_deleted       BIT(1) DEFAULT 0      NOT NULL,
    CONSTRAINT pk_general_board PRIMARY KEY (general_board_id)
);

CREATE TABLE general_comment
(
    general_comment_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id            BIGINT                NULL,
    general_post_id    BIGINT                NULL,
    content            VARCHAR(255)          NOT NULL,
    created_at         datetime              NOT NULL,
    CONSTRAINT pk_general_comment PRIMARY KEY (general_comment_id)
);

CREATE TABLE general_post
(
    general_post_id  BIGINT AUTO_INCREMENT NOT NULL,
    user_id          BIGINT                NULL,
    general_board_id BIGINT                NULL,
    title            VARCHAR(255)          NOT NULL,
    content          VARCHAR(255)          NOT NULL,
    is_hidden        BIT(1) DEFAULT 0      NOT NULL,
    view             BIGINT                NOT NULL,
    created_at       datetime              NULL,
    updated_at       datetime              NULL,
    CONSTRAINT pk_general_post PRIMARY KEY (general_post_id)
);

CREATE TABLE quiz_board
(
    quiz_board_id BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255)          NOT NULL,
    user_id       BIGINT                NOT NULL,
    created_at    datetime              NULL,
    is_deleted    BIT(1) DEFAULT 0      NOT NULL,
    CONSTRAINT pk_quiz_board PRIMARY KEY (quiz_board_id)
);

CREATE TABLE quiz_comment
(
    quiz_comment_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id         BIGINT                NULL,
    quiz_post_id    BIGINT                NULL,
    content         VARCHAR(255)          NOT NULL,
    created_at      datetime              NOT NULL,
    CONSTRAINT pk_quiz_comment PRIMARY KEY (quiz_comment_id)
);

CREATE TABLE quiz_post
(
    quiz_post_id  BIGINT AUTO_INCREMENT NOT NULL,
    user_id       BIGINT                NULL,
    quiz_board_id BIGINT                NULL,
    quiz_title    VARCHAR(255)          NOT NULL,
    hint_content  VARCHAR(255)          NOT NULL,
    answer        VARCHAR(255)          NOT NULL,
    is_hidden     BIT(1) DEFAULT 0      NOT NULL,
    view          BIGINT                NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    CONSTRAINT pk_quiz_post PRIMARY KEY (quiz_post_id)
);

CREATE TABLE users
(
    user_id       BIGINT AUTO_INCREMENT NOT NULL,
    email         VARCHAR(255)          NOT NULL,
    password      VARCHAR(255)          NOT NULL,
    nickname      VARCHAR(255)          NOT NULL,
    score         INT                   NOT NULL,
    created_at    datetime              NOT NULL,
    connected_at  datetime              NULL,
    connect_count INT                   NOT NULL,
    is_deleted    BIT(1)                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE board_manager_apply
    ADD CONSTRAINT FK_BOARD_MANAGER_APPLY_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE general_comment
    ADD CONSTRAINT FK_GENERAL_COMMENT_ON_GENERAL_POST FOREIGN KEY (general_post_id) REFERENCES general_post (general_post_id);

ALTER TABLE general_comment
    ADD CONSTRAINT FK_GENERAL_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE general_post
    ADD CONSTRAINT FK_GENERAL_POST_ON_GENERAL_BOARD FOREIGN KEY (general_board_id) REFERENCES general_board (general_board_id);

ALTER TABLE general_post
    ADD CONSTRAINT FK_GENERAL_POST_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE quiz_comment
    ADD CONSTRAINT FK_QUIZ_COMMENT_ON_QUIZ_POST FOREIGN KEY (quiz_post_id) REFERENCES quiz_post (quiz_post_id);

ALTER TABLE quiz_comment
    ADD CONSTRAINT FK_QUIZ_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE quiz_post
    ADD CONSTRAINT FK_QUIZ_POST_ON_QUIZ_BOARD FOREIGN KEY (quiz_board_id) REFERENCES general_board (general_board_id);

ALTER TABLE quiz_post
    ADD CONSTRAINT FK_QUIZ_POST_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);