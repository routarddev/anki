package com.anki.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author routarddev
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game implements Serializable {

    private ArrayList<Card> redBox;
    private ArrayList<Card> orangeBox;
    private ArrayList<Card> greenBox;

    public void init() {
        redBox = new ArrayList<Card>();
        orangeBox = new ArrayList<Card>();
        greenBox = new ArrayList<Card>();
    }

    public void addCardToOrangeBox(Card card) {
        orangeBox.add(card);
    }

    public void addCardToGreenBox(Card card) {
        greenBox.add(card);
    }

    public boolean isEndOfGame() {
        return redBox.isEmpty() && orangeBox.isEmpty();
    }

    public void endSession() {
        redBox.addAll(orangeBox);
        orangeBox.clear();
        orangeBox.addAll(greenBox);
        greenBox.clear();
    }

}
