package com.anki.controller;

import com.anki.model.Card;
import com.anki.model.Game;
import com.anki.processors.SaveRecoverGame;
import com.anki.utils.Constants;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller class for the game and user interaction
 * @author routarddev
 */
@NoArgsConstructor
public final class InteractionController {

    /**
     * Logic of the game
     * @param game current game status
     */
    public void play(Game game) {
        String input, answer, studentName;
        Scanner scanner = new Scanner(System.in);

        System.out.println(Constants.START_MSG);
        System.out.println(Constants.ASK_STUDENT_NAME);
        studentName = scanner.nextLine();
        System.out.println(String.format(Constants.WELCOME_STUDENT, studentName));
        System.out.println(Constants.INSTRUCTIONS_HELP);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("Y"))
            System.out.println(Constants.INSTRUCTIONS);

        if (!game.getRedBox().isEmpty()) {
            ArrayList<Card> redBox = (ArrayList<Card>) game.getRedBox().clone();
            for(Card card: redBox) {
                printDeck(game);
                System.out.println(String.format(Constants.QUESTION, card.getCardId(), card.getQuestion()));
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase(card.getAnswer())) {
                    System.out.println(Constants.CORRECT);
                    game.addCardToGreenBox(card);
                    game.getRedBox().remove(card);
                } else if (card.getAnswer().contains(answer)) {
                    System.out.println(Constants.PARTIAL_ANSWER);
                    game.addCardToOrangeBox(card);
                    game.getRedBox().remove(card);
                } else {
                    System.out.println(Constants.WRONG_ANSWER);
                }

                if (game.getRedBox().size() > 0) {
                    System.out.println(Constants.NEXT_STEP);
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("Q")) break;
                }
            }
        }
        scanner.close();

        if (game.isEndOfGame()) {
            cleanGameStatus();
            System.out.println(String.format(Constants.CONGRATULATIONS, studentName));
        } else { //User quited the game
            SaveRecoverGame saveGame = new SaveRecoverGame();
            try {
                game.endSession();
                saveGame.writeObject(game);
                System.out.println(Constants.SAVE_GAME);
            } catch(IOException ex) {
                System.out.println(Constants.IO_EXCEPTION_MSG);
                System.exit(0);
            }
        }
        System.out.println(String.format(Constants.END_MSG, studentName));
    }

    /**
     * Prompts the cards of each box in the current game.
     * @param game current game status
     */
    public void printDeck(Game game) {
        System.out.println("\n" + Constants.HEADER_LINES);
        System.out.println(Constants.HEADER);
        System.out.println(Constants.HEADER_LINES);
        String line = "|";
        line += mountCardLine(game.getRedBox());
        line += mountCardLine(game.getOrangeBox());
        line += mountCardLine(game.getGreenBox());
        System.out.println(line + "\n");
    }

    /**
     * Concatenates a string with the card ID's of the incoming box
     * @param list box of cards
     * @return line string with the card ID's
     */
    public String mountCardLine(ArrayList<Card> list) {
        String line = "   ";
        if (list.isEmpty()) line += " - ";
        for(Card card: list) {
            line += card.getCardId() + ", ";
        }
        line += "   |";
        return line;
    }

    /**
     * Prompts a message when some parameters are missing
     *  in the program call.
     */
    public void help() {
        System.out.println(Constants.HELP);
    }

    /**
     * If the game is over, delete the serialization file
     * @return boolean true if file has been successfully deleted,
     *  false otherwise.
     */
    public boolean cleanGameStatus() {
        File file = new File(Constants.GAME_FILE_PATH);
        if (file.exists())
            return file.delete();
        return false;
    }

}
