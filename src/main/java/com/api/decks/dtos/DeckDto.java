package com.api.decks.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DeckDto {

    @NotBlank
    private String deckName;

    @NotNull
    private UUID player_id;

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public UUID getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(UUID player_id) {
        this.player_id = player_id;
    }
}
