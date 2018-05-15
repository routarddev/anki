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
 * @author routarddev
 */
@NoArgsConstructor
public final class InteractionHelper {

    /**
     * Prompts the cards of each box in the current game.
     * @param game current game status
     */
    public void printDeck(Game game) {
        System.out.println("-------------------------------------------------------");
        System.out.println("|   RED BOX   ||     ORANGE BOX      ||   GREEN BOX   |");
        System.out.println("-------------------------------------------------------");
        String line = "";
        printCardLine(game.getRedBox(), line);
        printCardLine(game.getOrangeBox(), line);
        printCardLine(game.getGreenBox(), line);
    }

    /**
     * Concatenates a string with the card ID's of the incoming box
     * @param list box of cards
     * @param line string with the card ID's
     */
    public void printCardLine(ArrayList<Card> list, String line) {
        line.concat("| ");
        for(Card card: list) {
            line.concat(card.getCardId() + ", ");
        }
        line.concat(" |");
    }

    /**
     * Logic of the game
     * @param game current game status
     */
    public void play(Game game) {
        String input, answer, studentName;
        Scanner scanner = new Scanner(System.in);

        System.out.println(Constants.START);
        System.out.println("What's your name? ");
        studentName = scanner.nextLine();

        if (!game.getRedBox().isEmpty()) {
            ArrayList<Card> redBox = (ArrayList<Card>) game.getRedBox().clone();
            for(Card card: redBox) {
                System.out.println("Card " + card.getCardId() + ": " + card.getQuestion());
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase(card.getAnswer())) {
                    System.out.println("That's correct, congratulations!");
                    game.addCardToGreenBox(card);
                    game.getRedBox().remove(card);
                } else if (card.getAnswer().contains(answer)) {
                    System.out.println("That's partially correct, you should review the subject");
                    game.addCardToOrangeBox(card);
                    game.getRedBox().remove(card);
                }

                if (game.getRedBox().size() > 0) {
                    System.out.println("Quit (Q) or Next question (N)? (Q/N)");
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
            } catch(IOException ex) {
                System.out.println("error message");
                System.exit(0);
            }
        }
        System.out.println(String.format(Constants.END, studentName));
    }

    /**
     * Prompts a message when some parameters are missing
     *  in the program call.
     */
    public void help() {
        System.out.println("Filename argument was missing.");
        System.out.println("Run the command again: ");
        System.out.println("    java anki filename");
    }

    /**
     * If the game is over, delete the serialization file
     */
    public void cleanGameStatus() {
        File file = new File(Constants.GAME_FILE_PATH);
        if(file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
        }
    }

}
