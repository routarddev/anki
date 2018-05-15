package com.anki.processors;

import com.anki.model.Card;
import com.anki.utils.Constants;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author routarddev
 */
@AllArgsConstructor
public class CardsReader {

    private String fileName;

    /**
     * Read and process a file with all cards of the game
     * @param deckOfCards all cards of the game
     * @throws IOException file reading exception, to be passed at the calling method
     */
    public void readDeckFromFile(ArrayList<Card> deckOfCards) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        //Don't process file headers
        String line = br.readLine();
        int index = 0;
        while ((line = br.readLine()) != null) {
            if (line.contains(Constants.LINE_SEPARATOR)) {
                String[] text = line.split("\\" + Constants.LINE_SEPARATOR);
                Card card = new Card();
                card.setCardId(++index);
                card.setQuestion(text[0]);
                card.setAnswer(text[1]);
                deckOfCards.add(card);
            }
        }
    }

}
