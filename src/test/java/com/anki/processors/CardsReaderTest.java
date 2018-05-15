package com.anki.processors;

import com.anki.model.Card;
import com.anki.utils.Constants;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by hadrien on 13/05/18.
 */
public class CardsReaderTest {

    private CardsReader cardsReader;
    private static final int NUMBER_OF_CARDS = 3;

    @Test
    public void readDeckFromFileTest() {
        ArrayList<Card> deckOfCards = new ArrayList<Card>();
        cardsReader = new CardsReader(Constants.CARD_FILE_PATH);
        //Files.lines(Paths.get(new File("D:/tmp/nourit.txt").getPath())).count();
        try {
            cardsReader.readDeckFromFile(deckOfCards);
        } catch(IOException ex) {
        }
        assertNotNull(deckOfCards);
        assertEquals(NUMBER_OF_CARDS, deckOfCards.size());
    }


    @Test(expected = IOException.class)
    public void failToReadDeckFromFile() throws IOException {
        ArrayList<Card> deckOfCards = new ArrayList<Card>();
        cardsReader = new CardsReader(Constants.CARDS_FILE);
        cardsReader.readDeckFromFile(deckOfCards);
    }

}
