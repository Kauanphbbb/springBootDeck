package com.api.decks.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "decks")
public class DeckModel {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String deckName;

    @ManyToMany()
    @JsonIgnoreProperties({"player", "deck"})
    @JoinTable(name = "decks_cards",
            joinColumns = {@JoinColumn(name = "deck_id")},
            inverseJoinColumns = {@JoinColumn(name = "card_id")})
    private Set<CardModel> cardInDeck;

    @ManyToOne()
    @JoinColumn(name = "player_id")
    @JsonIgnoreProperties({"decks", "cards", "id"})
    private PlayerModel owner;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        DeckModel.serialVersionUID = serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public Set<CardModel> getCardInDeck() {
        return cardInDeck;
    }

    public void setCardInDeck(Set<CardModel> cardInDeck) {
        this.cardInDeck = cardInDeck;
    }

    public PlayerModel getOwner() {
        return owner;
    }

    public void setOwner(PlayerModel owner) {
        this.owner = owner;
    }
}
