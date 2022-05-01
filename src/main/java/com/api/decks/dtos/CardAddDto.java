package com.api.decks.dtos;

import java.util.UUID;

public class CardAddDto {
    private UUID card_id;

    public UUID getCard_id() {
        return card_id;
    }

    public void setCard_id(UUID card_id) {
        this.card_id = card_id;
    }
}
