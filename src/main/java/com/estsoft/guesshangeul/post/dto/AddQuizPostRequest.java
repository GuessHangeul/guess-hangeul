package com.estsoft.guesshangeul.post.dto;


import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.user.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Getter
@Setter
@NoArgsConstructor
public class AddQuizPostRequest {
    private Users users;
    private QuizBoard quizBoardId;
    private String quizTitle;
    private String hintContent;
    private boolean isHidden;
    private Long view;

    public QuizPost toEntity() {
        QuizPost quizPost = new QuizPost();
        quizPost.setUser(this.users);
        quizPost.setQuizBoard(this.quizBoardId);
        quizPost.setQuizTitle(this.quizTitle);
        quizPost.setHintContent(this.hintContent);
        quizPost.setHidden(this.isHidden);
        quizPost.setView(this.view);
        return quizPost;
    }
}
