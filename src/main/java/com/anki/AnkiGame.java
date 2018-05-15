package com.anki;

import com.anki.controller.InteractionHelper;
import com.anki.model.Card;
import com.anki.model.Game;
import com.anki.processors.CardsReader;
import com.anki.processors.SaveRecoverGame;
import com.anki.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author routarddev
 */
public final class AnkiGame {

    public static void main(String[] args) {

        InteractionHelper interactionHelper = new InteractionHelper();

        //TEST
        args = new String[2];
        args[0]=Constants.CARD_FILE_PATH;
        //TEST

        if (args.length < 1) {
            interactionHelper.help();
            System.exit(0);
        }

        /*
        //Read arguments
        if (args.length > 0 && !args[0].isEmpty()) {
            inputFileFullPath = args[0];
            File inputFile = new File(inputFileFullPath);
            if (!inputFile.exists()) {
                inputFileFullPath = Constants.INPUT_FILE;
            }
        }
        */

        Game game = new Game();

        //if serialization file !exists then start new game:
        File gameStatus = new File(Constants.FILE_PATH+Constants.GAME_STATUS_FILE);
        if (!gameStatus.exists()) {
            CardsReader cardsReader = new CardsReader(args[0]);
            ArrayList<Card> deckOfCards = new ArrayList<Card>();
            try {
                cardsReader.readDeckFromFile(deckOfCards);
            } catch (IOException ex) {
                System.out.println("error message");
                System.exit(-1);
            }
            game.init();
            game.setRedBox(deckOfCards);

            //else, deserialize and follow game
        } else {
            SaveRecoverGame recoverGame = new SaveRecoverGame();
            try {
                recoverGame.readObject(game);
            } catch(IOException ex) {
                System.out.println("error message");
                System.exit(0);
            } catch(ClassNotFoundException ex) {
                System.out.println("error message");
                System.exit(0);
            }
        }

        interactionHelper.play(game);
    }

}
