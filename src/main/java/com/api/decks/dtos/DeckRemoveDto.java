package com.api.decks.dtos;

import java.util.UUID;

public class DeckRemoveDto {
    private UUID playerId;

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}
