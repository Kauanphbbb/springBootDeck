package com.api.decks.controllers;

import com.api.decks.dtos.CardDto;
import com.api.decks.models.CardModel;
import com.api.decks.services.CardService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cards")
public class CardController {

    final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Object> save (@RequestBody @Valid CardDto cardDto) {
        boolean validLanguage = cardDto.getLanguage().equals("jp") || cardDto.getLanguage().equals("pt-br") || cardDto.getLanguage().equals("en");
        if(!validLanguage) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid language");
        }
        var cardModel = new CardModel();
        BeanUtils.copyProperties(cardDto, cardModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.save(cardModel));
    }

    @GetMapping
    public ResponseEntity<List<CardModel>> getAll () {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "id") UUID id) {
        Optional<CardModel> cardModelOptional = cardService.findOne(id);
        if(cardModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cardModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<CardModel> cardModelOptional = cardService.findOne(id);
        if(cardModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }

        cardService.delete(cardModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Card deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid CardDto cardDto) {
        Optional<CardModel> cardModelOptional = cardService.findOne(id);
        if(cardModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }

        var cardModel = new CardModel();
        BeanUtils.copyProperties(cardDto, cardModel);
        cardModel.setId(cardModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(cardService.save(cardModel));
    }
}
