package com.anki;

import com.anki.controller.InteractionController;
import com.anki.model.Card;
import com.anki.model.Game;
import com.anki.processors.CardsReader;
import com.anki.processors.SaveRecoverGame;
import com.anki.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main program for playing Anki
 * @author routarddev
 */
public final class AnkiGame {

    public static void main(String[] args) {

        InteractionController interactionController = new InteractionController();

        //Read arguments
        //If no file has been provided, use the default one
        String cardsFileName = null;
        if (args.length > 0) {
            cardsFileName = args[0];
            File inputFile = new File(cardsFileName);
            if (!inputFile.exists()) cardsFileName = Constants.CARD_FILE_PATH;
        } else {
            cardsFileName = Constants.CARD_FILE_PATH;
        }

        Game game = new Game();

        //If serialization file doesn't exist, then start new game:
        File gameStatus = new File(Constants.FILE_PATH+Constants.GAME_STATUS_FILE);
        if (!gameStatus.exists()) {
            CardsReader cardsReader = new CardsReader(cardsFileName);
            ArrayList<Card> deckOfCards = new ArrayList<Card>();
            try {
                cardsReader.readDeckFromFile(deckOfCards);
            } catch (IOException ex) {
                System.out.println(String.format(Constants.IO_EXCEPTION_MSG, ex.getMessage()));
                System.exit(-1);
            }
            game.init();
            game.setRedBox(deckOfCards);

        } else {
            //Deserialize and follow the game
            SaveRecoverGame recoverGame = new SaveRecoverGame();
            try {
                game = recoverGame.readObject();
            } catch(IOException ex) {
                System.out.println(String.format(Constants.IO_EXCEPTION_MSG, ex.getMessage()));
                System.exit(-1);
            } catch(ClassNotFoundException ex) {
                System.out.println(String.format(Constants.CLASS_EXCEPTION_MSG, ex.getMessage()));
                System.exit(-2);
            }
        }

        interactionController.play(game);
    }

}
