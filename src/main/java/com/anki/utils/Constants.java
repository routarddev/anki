package com.anki.utils;

/**
 * @author routarddev
 */
public final class Constants {

    //User interaction constants
    public static final String START_MSG = "Welcome to Anki (暗記)";
    public static final String CONGRATULATIONS = "Well done %s!!";
    public static final String END_MSG = "Good bye %s!";
    public static final String ASK_STUDENT_NAME = "What's your name?";
    public static final String WELCOME_STUDENT = "Welcome %s!";
    public static final String INSTRUCTIONS_HELP = "Do you want to read the instructions? (y/n)";
    public static final String INSTRUCTIONS =
            "\nHere is a deck of cards of the subject to study.\n" +
            "One face of the card contains a question, the other face the answer.\n"+
            "You have to study the questions and try to guess the answer.\n\n" +
            "If you don't know the answer of the Card, it will be placed in the red box.\n" +
            "If you know a part of the answer of the Card, it will be placed in the orange box.\n" +
            "If you know the answer of the Card, it will be placed in the green box.\n\n" +
            "When all the card has been seen, the session is over.\n";
    public static final String QUESTION = "Card %d: %s";
    public static final String CORRECT = "That's correct, congratulations!\n";
    public static final String PARTIAL_ANSWER = "That's partially correct, you should review the subject.\n";
    public static final String WRONG_ANSWER = "That's not correct, review again the subject.\n";
    public static final String NEXT_STEP = "Quit (Q) or Next question (N)? (Q/N)";
    public static final String SAVE_GAME = "\nAnki studying status has been successfully saved.";

    public static final String HEADER_LINES =   "-------------------------------------------------";
    public static final String HEADER =         "|   RED BOX   |   ORANGE BOX    |   GREEN BOX   |";

    //Error messages
    public static final String HELP =   "Filename argument was missing.\n" +
                                        "        Run the command again:\n" +
                                        "            java anki filename";

    public static final String IO_EXCEPTION_MSG =   "I/O Error: %s\n\n" +"" +
                                                    "Contact the developer. Sorry for the inconvenience.";

    public static final String CLASS_EXCEPTION_MSG =    "Class Not Found Error: %s\n\n" +
                                                        "Contact the developer. Sorry for the inconvenience.";

    //Logic constants
    public static final String FILE_PATH = "resources/";
    public static final String GAME_STATUS_FILE = "anki.dat";
    public static final String CARDS_FILE = "cards.txt";
    public static final String CARD_FILE_PATH = FILE_PATH + CARDS_FILE;
    public static final String GAME_FILE_PATH = FILE_PATH + GAME_STATUS_FILE;
    public static final String LINE_SEPARATOR = "|";

}
