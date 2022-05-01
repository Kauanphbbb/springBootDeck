package com.api.decks.controllers;


import com.api.decks.dtos.CardAddDto;
import com.api.decks.dtos.DeckAddCardDto;
import com.api.decks.dtos.DeckRemoveDto;
import com.api.decks.dtos.PlayerDto;
import com.api.decks.models.CardModel;
import com.api.decks.models.DeckModel;
import com.api.decks.models.PlayerModel;
import com.api.decks.services.CardService;
import com.api.decks.services.DeckService;
import com.api.decks.services.PlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/players")
public class PlayerController {
    final PlayerService playerService;
    final CardService cardService;
    final DeckService deckService;

    public PlayerController(PlayerService playerService, CardService cardService, DeckService deckService) {
        this.playerService = playerService;
        this.cardService = cardService;
        this.deckService = deckService;
    }
    @Transactional
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PlayerDto playerDto) {
        if(playerService.findByNickName(playerDto.getNickname())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nickname already in use");
        }
        var playerModel = new PlayerModel();
        BeanUtils.copyProperties(playerDto, playerModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(playerModel));
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<Object> addCard(@PathVariable(value = "id") UUID id,
                                          @RequestBody CardAddDto cardAddDto) {

        Optional<PlayerModel> playerOptional = playerService.findOne(id);
        if(playerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        Optional<CardModel> cardModelOptional = cardService.findOne(cardAddDto.getCard_id());
        if(cardModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }

        List<CardModel> cards = new ArrayList<CardModel>();

        PlayerModel player = playerOptional.get();
        CardModel card = cardModelOptional.get();
        cards.add(card);
        player.getCards().addAll(cards);

        return ResponseEntity.status(HttpStatus.OK).body("Card added");
    }

    @Transactional
    @PostMapping("/addToDeck/{id}")
    public ResponseEntity<Object> addCardToADeck(@PathVariable(value = "id") UUID id,
                                                 @RequestBody DeckAddCardDto deckAddCardDto) {
        Optional<PlayerModel> playerOptional = playerService.findOne(id);
        if(playerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        Optional<CardModel> cardModelOptional = cardService.findOne(deckAddCardDto.getCard_id());
        if(cardModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }

        Optional<DeckModel> deckModelOptional = deckService.findById(deckAddCardDto.getDeck_id());
        if(deckModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("deck not found");
        }

        if(!deckModelOptional.get().getOwner().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("this is not your deck");
        }

        List<CardModel> cards = new ArrayList<CardModel>();
        CardModel card = cardModelOptional.get();
        DeckModel deck = deckModelOptional.get();
        cards.add(card);
        deck.getCardInDeck().addAll(cards);
        return ResponseEntity.status(HttpStatus.OK).body("Card " +
                card.getCardName() +
                " added to deck " +
                deck.getDeckName());
    }

    @Transactional
    @DeleteMapping("/removeFromDeck/{id}")
    public ResponseEntity<Object> removeCardFromDeck(@PathVariable(value = "id") UUID id,
                                                     @RequestBody DeckAddCardDto deckAddCardDto) {
        Optional<PlayerModel> playerOptional = playerService.findOne(id);
        if(playerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        Optional<CardModel> cardModelOptional = cardService.findOne(deckAddCardDto.getCard_id());
        if(cardModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }

        Optional<DeckModel> deckModelOptional = deckService.findById(deckAddCardDto.getDeck_id());
        if(deckModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("deck not found");
        }

        if(!deckModelOptional.get().getOwner().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("this is not your deck");
        }

        DeckModel deckModel = deckModelOptional.get();
        CardModel cardModel = cardModelOptional.get();
        boolean cardInDeck = deckModel.getCardInDeck().contains(cardModel);

        if(!cardInDeck) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This card is not on your deck");
        }

        deckModel.getCardInDeck().remove(cardModel);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Card " +
                cardModel.getCardName() +
                " removed from " +
                deckModel.getDeckName());
    }
    @GetMapping
    public ResponseEntity<List<PlayerModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById (@PathVariable(value = "id") UUID id) {

        Optional<PlayerModel> playerOptional = playerService.findOne(id);
        if(playerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(playerOptional);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<Object> changeNickname (@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid PlayerDto playerDto) {
        if(playerService.findByNickName(playerDto.getNickname())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nickname already in use");
        }

        Optional<PlayerModel> playerOptional = playerService.findOne(id);
        if(playerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        var playerModel = new PlayerModel();
        BeanUtils.copyProperties(playerDto, playerModel);
        playerModel.setId(playerOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(playerService.save(playerModel));
    }

}
