package com.api.decks.repositories;

import com.api.decks.models.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardModel, UUID> {
}
