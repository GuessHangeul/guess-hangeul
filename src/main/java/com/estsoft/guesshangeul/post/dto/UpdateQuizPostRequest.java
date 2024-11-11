package com.estsoft.guesshangeul.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateQuizPostRequest {
    private Long id;
    private String quizTitle;
    private String hintContent;
    private boolean isHidden;
    private Long view;
}