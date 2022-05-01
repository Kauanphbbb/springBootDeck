package com.api.decks.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cards")
public class CardModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String cardName;

    @Column(nullable = false, length = 100)
    private String edition;

    @Column(nullable = false, length = 30)
    private String language;

    @Column(nullable = false)
    private boolean foil;

    @Column(nullable = false)
    private double value;

    @ManyToMany(mappedBy = "cards")
    Set<PlayerModel> player;

    @ManyToMany(mappedBy = "cardInDeck")
    Set<DeckModel> deck;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isFoil() {
        return foil;
    }

    public void setFoil(boolean foil) {
        this.foil = foil;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Set<PlayerModel> getPlayer() {
        return player;
    }

    public void setPlayer(Set<PlayerModel> player) {
        this.player = player;
    }

    public Set<DeckModel> getDeck() {
        return deck;
    }

    public void setDeck(Set<DeckModel> deck) {
        this.deck = deck;
    }
}
