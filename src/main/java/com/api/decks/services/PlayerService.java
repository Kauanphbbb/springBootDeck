package com.api.decks.services;

import com.api.decks.models.PlayerModel;
import com.api.decks.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    final PlayerRepository playerRepository;


    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public PlayerModel save(PlayerModel player) {
        return playerRepository.save(player);
    }

    public List<PlayerModel> getAll() {
        return playerRepository.findAll();
    }

    public Optional<PlayerModel> findOne(UUID id) {
        return playerRepository.findById(id);
    }

    public boolean findByNickName(String nickname) {
        return playerRepository.existsByNickname(nickname);
    }
}
