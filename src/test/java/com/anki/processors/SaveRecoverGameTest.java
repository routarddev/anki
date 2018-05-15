package com.anki.processors;

import com.anki.model.Card;
import com.anki.model.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author routarddev
 */
public class SaveRecoverGameTest {

    private SaveRecoverGame saveRecoverGame;
    private String serializationPath = "src/test/resources/";
    private String serializationTestFile = serializationPath + "serializationTest.dat";
    private String deserializationTestFile = serializationPath + "deserializationTest.dat";
    private String fullTestFile = serializationPath + "serializeDeserializeTest.dat";

    @Before //Setup
    public void before() {
        //Prepare file for the deserialization test
        saveRecoverGame = new SaveRecoverGame(deserializationTestFile);
        Game game = createTestGame();
        try {
            saveRecoverGame.writeObject(game);
        } catch(Exception ex) {
        }
    }

    @Test
    public void serializationTest() {
        saveRecoverGame = new SaveRecoverGame(serializationTestFile);
        Game game = createTestGame();
        try {
            saveRecoverGame.writeObject(game);
        } catch(Exception ex) {
        }
        File serializedFile = new File(serializationTestFile);
        assertNotNull(serializedFile);
        assertTrue(serializedFile.exists());
    }

    @Test
    public void deserializationTest() {
        saveRecoverGame = new SaveRecoverGame(deserializationTestFile);
        Game expectedGame = createTestGame();
        Game deserializedGame = null;
        try {
            deserializedGame = saveRecoverGame.readObject();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        assertNotNull(deserializedGame);
        assertEquals(expectedGame, deserializedGame);
    }

    @Test
    public void serializeDeserializeTest() {
        saveRecoverGame = new SaveRecoverGame(fullTestFile);
        Game expectedGame = createTestGame();
        Game game = null;
        try {
            saveRecoverGame.writeObject(expectedGame);
            game = saveRecoverGame.readObject();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        assertNotNull(game);
        assertEquals(expectedGame, game);
    }

    @After // tearDown()
    public void after() {
        deleteFile(serializationTestFile);
        deleteFile(deserializationTestFile);
        deleteFile(fullTestFile);
    }

    private void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) file.delete();
    }

    private Game createTestGame() {
        Game game = new Game();
        ArrayList<Card> listOfCards = new ArrayList<Card>();

        listOfCards.add(new Card(1, "Question 1", "Answer 1"));
        game.setRedBox(listOfCards);
        listOfCards.clear();

        listOfCards.add(new Card(2, "Question 2", "Answer 2"));
        game.setGreenBox(listOfCards);
        listOfCards.clear();

        listOfCards.add(new Card(3, "Question 3", "Answer 3"));
        game.setOrangeBox(listOfCards);

        return game;
    }

}
