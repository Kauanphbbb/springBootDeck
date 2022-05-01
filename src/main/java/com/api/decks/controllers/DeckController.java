package com.api.decks.controllers;

import com.api.decks.dtos.DeckDto;
import com.api.decks.dtos.DeckRemoveDto;
import com.api.decks.models.DeckModel;
import com.api.decks.models.PlayerModel;
import com.api.decks.services.DeckService;
import com.api.decks.services.PlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/decks")
public class DeckController {

    final DeckService deckService;
    final PlayerService playerService;

    public DeckController(DeckService deckService, PlayerService playerService) {
        this.deckService = deckService;
        this.playerService = playerService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DeckDto deckDto) {
        Optional<PlayerModel> playerOptional = playerService.findOne(deckDto.getPlayer_id());
        if(playerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        PlayerModel player = playerOptional.get();
        var deckModel = new DeckModel();
        BeanUtils.copyProperties(deckDto, deckModel);
        deckModel.setOwner(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(deckService.save(deckModel));
    }

    @GetMapping
    public ResponseEntity<List<DeckModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(deckService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<DeckModel> deckOptional = deckService.findById(id);
        if(deckOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("deck not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(deckOptional);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<Object> changeDeckName(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid DeckDto deckDto) {
        Optional<DeckModel> deckOptional = deckService.findById(id);
        if(deckOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("deck not found");
        }

        if(!deckOptional.get().getOwner().getId().equals(deckDto.getPlayer_id())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("this is not your deck");
        }

        var deckModel = new DeckModel();
        BeanUtils.copyProperties(deckDto, deckModel);
        deckModel.setId(deckOptional.get().getId());
        deckModel.setOwner(deckOptional.get().getOwner());

        return ResponseEntity.status(HttpStatus.OK).body(deckService.save(deckModel));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeDeck(@PathVariable(value = "id") UUID id,
                                             @RequestBody DeckRemoveDto deckRemoveDto){
        Optional<DeckModel> deckOptional = deckService.findById(id);
        if(deckOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("deck not found");
        }

        if(!deckOptional.get().getOwner().getId().equals(deckRemoveDto.getPlayerId())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("this is not your deck");
        }

        deckService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Deck " +
                deckOptional.get().getDeckName() +
                " deleted");
    }
}
