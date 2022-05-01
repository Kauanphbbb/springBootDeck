package com.api.decks.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "players")
public class PlayerModel {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100, unique = true)
    private String nickname;

    @ManyToMany()
    @JsonIgnoreProperties({"player", "deck"})
    @JoinTable(name = "player_cards",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = "card_id"))
    private Set<CardModel> cards;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties("owner")
    private Set<DeckModel> decks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        PlayerModel.serialVersionUID = serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<CardModel> getCards() {
        return cards;
    }

    public void setCards(Set<CardModel> cards) {
        this.cards = cards;
    }

    public Set<DeckModel> getDecks() {
        return decks;
    }

    public void setDecks(Set<DeckModel> decks) {
        this.decks = decks;
    }
}
