package com.estsoft.guesshangeul.post.dto;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class GeneralPostResponse {
    private Long id;
    private Users users;
    private GeneralBoard generalBoard;
    private String title;
    private String content;
    private boolean isHidden;
    private Long view;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public GeneralPostResponse(GeneralPost generalPost) {
        this.id = generalPost.getId();
        this.users = generalPost.getUsers();
        this.generalBoard = generalPost.getGeneralBoard();
        this.title = generalPost.getTitle();
        this.content = generalPost.getContent();
        this.isHidden = generalPost.isHidden();
        this.view = generalPost.getView();
        this.createdAt = generalPost.getCreatedAt();
        this.updatedAt = generalPost.getUpdatedAt();
    }

    public GeneralPostResponse(GeneralPostResponse generalPostResponse) {

    }
}