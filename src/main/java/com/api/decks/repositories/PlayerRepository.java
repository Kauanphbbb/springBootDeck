package com.api.decks.repositories;

import com.api.decks.models.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, UUID> {
    boolean existsByNickname(String nickname);
}
