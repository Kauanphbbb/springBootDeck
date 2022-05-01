package com.api.decks.services;

import com.api.decks.models.CardModel;
import com.api.decks.repositories.CardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public CardModel save(CardModel cardModel) {
        return cardRepository.save(cardModel);
    }

    @Transactional
    public void delete(CardModel cardModel) { cardRepository.delete(cardModel); }

    public List<CardModel> getAll() { return cardRepository.findAll(); }

    public Optional<CardModel> findOne(UUID id) { return cardRepository.findById(id); }
}
