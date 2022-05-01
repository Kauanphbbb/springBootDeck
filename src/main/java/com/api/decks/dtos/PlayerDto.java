package com.api.decks.dtos;

import javax.validation.constraints.NotBlank;

public class PlayerDto {

    @NotBlank
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
