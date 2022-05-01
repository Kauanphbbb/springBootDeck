package com.api.decks.dtos;

import java.util.UUID;

public class DeckAddCardDto {
    private UUID deck_id;
    private UUID card_id;

    public UUID getCard_id() {
        return card_id;
    }

    public void setCard_id(UUID card_id) {
        this.card_id = card_id;
    }

    public UUID getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(UUID deck_id) {
        this.deck_id = deck_id;
    }
}
