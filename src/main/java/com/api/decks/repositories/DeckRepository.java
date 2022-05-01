package com.api.decks.repositories;

import com.api.decks.models.DeckModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeckRepository extends JpaRepository<DeckModel, UUID> {
    boolean existsByDeckName (String deckName);
}
