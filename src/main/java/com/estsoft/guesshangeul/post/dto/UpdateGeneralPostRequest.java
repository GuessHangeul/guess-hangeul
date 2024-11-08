package com.estsoft.guesshangeul.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateGeneralPostRequest {
    private String title;
    private String content;
    private boolean isHidden;
    private Long view;
}