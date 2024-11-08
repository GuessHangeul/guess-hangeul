package com.estsoft.guesshangeul.post.dto;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.user.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddGeneralPostRequest {
    private Users users;
    private GeneralBoard generalBoard;
    private String title;
    private String content;
    private boolean isHidden;
    private Long view;

    public GeneralPost toEntity() {
        GeneralPost generalPost = new GeneralPost();
        generalPost.setUsers(this.users);
        generalPost.setGeneralBoard(this.generalBoard);
        generalPost.setTitle(this.title);
        generalPost.setContent(this.content);
        generalPost.setHidden(this.isHidden);
        generalPost.setView(this.view);
        return generalPost;
    }
}