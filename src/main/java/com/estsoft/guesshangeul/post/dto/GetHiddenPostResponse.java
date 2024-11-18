package com.estsoft.guesshangeul.post.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetHiddenPostResponse {
    private boolean isHidden;

    public GetHiddenPostResponse(boolean isHidden) {
        this.isHidden = isHidden;
    }
}
