package com.api.decks.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CardDto {

    @NotBlank
    private String cardName;
    @NotBlank
    private String edition;
    @NotBlank
    private String language;
    @NotNull
    private boolean foil;
    @NotNull
    private double value;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isFoil() {
        return foil;
    }

    public void setFoil(boolean foil) {
        this.foil = foil;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
