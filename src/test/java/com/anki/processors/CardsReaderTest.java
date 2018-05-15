package com.anki.processors;

import com.anki.model.Card;
import com.anki.utils.Constants;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author routarddev.
 */
public class CardsReaderTest {

    private CardsReader cardsReader;
    private static final int NUMBER_OF_CARDS = 3;

    @Test
    public void readDeckFromFileTest() {
        File file = new File(Constants.CARD_FILE_PATH);
        int numberOfCards = getNumberOfCards(file);
        if (numberOfCards == 0) numberOfCards = NUMBER_OF_CARDS;

        ArrayList<Card> deckOfCards = new ArrayList<Card>();
        cardsReader = new CardsReader(Constants.CARD_FILE_PATH);
        try {
            cardsReader.readDeckFromFile(deckOfCards);
        } catch(IOException ex) {
        }
        assertNotNull(deckOfCards);
        assertEquals(numberOfCards, deckOfCards.size());
    }


    @Test(expected = IOException.class)
    public void failToReadDeckFromFile() throws IOException {
        ArrayList<Card> deckOfCards = new ArrayList<Card>();
        cardsReader = new CardsReader(Constants.CARDS_FILE);
        cardsReader.readDeckFromFile(deckOfCards);
    }

    private int getNumberOfCards(File file) {
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] byteArray = new byte[(int) file.length()];
                fis.read(byteArray);
                String data = new String(byteArray);
                String[] stringArray = data.split("\n");
                return stringArray.length - 1;
            } catch(Exception ex) {
            }
        }
        return 0;
    }

}
