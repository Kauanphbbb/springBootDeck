package com.api.decks.services;

import com.api.decks.models.DeckModel;
import com.api.decks.repositories.DeckRepository;
import com.api.decks.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeckService {

    final DeckRepository deckRepository;


    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Transactional
    public DeckModel save(DeckModel deckModel) {
        return deckRepository.save(deckModel);
    }

    public Optional<DeckModel> findById(UUID id) {
        return deckRepository.findById(id);
    }

    public List<DeckModel> findAll() {
        return deckRepository.findAll();
    }

    public void delete(UUID id) {
        deckRepository.deleteById(id);
    }
}
