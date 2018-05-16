# Anki

### Problem to solve:

Anki is a spaced repetition flashcard program. Anki (暗記) is the Japanese word for memorization.

A student is proposed a **Deck** of cards, one face of the **card** contains a **question**, the other face the **answer**.
The **student** **studies** the question, tries to guess the answer and then looks if the answer he guessed was correct.

- If the student did not know the answer of the Card, he places the card in a **red box**
- If he did know a part of the answer of the Card, he places the card in a **orange box**
- If he did know the answer of the Card, he places the card in a **green box**

When all the card has been seen, the **session** is over : 

- The cards in the red box will be studied again the same day
- The cards in the orange box will be studied again the next day
- The cards in the green box, will be studied again two days later

For example:

The 1st of may, the student starts studying a deck of cards, it is composed of three cards A, B and C (sort in alphabetic order by question name).
The student puts the card A in the red box, the B card in the green box and the card C in the orange box.
There is no cards left to study, but one of them, the card A, has been put in the red box and he has to study it again, he partly knows it now and put it in the orange box.
The session of 1rst of may is over, the student takes the cards of the orange box and put them in the red box, and the cards from the green box in the orange.

The 2nd of may, the student starts another session, the cards he needs to study are in the red box. He studies the card A, and remember the answer pretty well and put it in the green box. He studies the card C and he stills have hard time to remember it perfeclty and put it again in the orange box.

The session of 2nd of may is over, the student takes the cards of the orange box and put them in the red box, and the cards from the green box in the orange.
The session goes on like this until all the cards are found in the green box at the end of a session !

---------------

The goal of the exercise is : based on a file containing all the cards of a deck about art, to simulate the study sessions day by day. The user will run the program every day and the session will be interactive. The state at the end of a session can be stored in a file, the program will then say goodby and stop.
When the user will run it again the next day it will automaticaly load the file containing the cards to study.
When the program will finish with all the cards in the green box, it will congratulate the user and quit.

Example of input file :
```csv
card question|card answer
What enzyme breaks down sugars mouth and digestive tract?|Amylase
How is dietary cholesterol transported to target tissues?|In chylomicrons
What is the glucose transporter in the brain and what are its properties?|GLUT-1 transports glucose across blood-brain barrier, GLUT-3 transports glucose into neurons.  Both are high-affinity.
```


## Design:
### Classes:
- **Card**: class that represents the card object, composed by: a cardId, which will be automatically assigned when reading from the cards file; the card question and the card answer.
- **Game**: class that represents the game status, composed by the three card boxes (ArrayList, in this case): red, orange and green.
- **Constants**: class containing literals used for the user interacion, messages, etc.
- **CardsReader**: helper class to read from the incoming/default cards file with answers and questions and process it into a general ArrayList with the full deck of cards.
- **SaveRecoverGame**: helper class used to serialize and deserialize a file containing the current status of the game, that is, to save the game status (Game and Card objects, which are serializable) into a file called ```anki.dat```, contained in the resources folder.
- **InteractionController**: class containing the main logic of the game and the user interaction, prompting messages and information and reading from the console.
- **AnkiGame**: main program to execute and run the game. A filename argument is expected so if it is found (should be stored in the resources folder), the cards are read from there, otherwise, we use the default file cards.txt and start the logic to play.

### Assumptions:
- A valid and existing path/name file for the cards is expected to be provided, if not, the
	default cards.txt file from the resources folder will be used.
- Taking into account the provided example input file input, which is reproduced in the cards.txt file, a header `card question|card answer` is expected, that will be omitted when reading, and each line containing the pair "card question|card answer" as is, using the vertical bar "|" as separator.
- The file cards.txt (or the provided file) containing the questions and answer of the deck of cards will only be read once, at the beginning of the program. After that, the information will be recovered from the serialization file.
- The option of quitting the game is offered, after which the program asks the student whether he/she will study again the same day, so the same session is saved, or the day session is over, so a cards movement will be performed from box to box.
- Regarding to the student answer treatment, to simplify the game logic (no AI or ML included), it will be as follows:
	- an answer is marked as correct (sent to the green box) if it is exactly as the expected answer, ignoring case considerations;
	- an answer is marked as partially correct (sent to the orange box) if the expected answer contains some words/characters of the answer provided by the student, ignoring case considerations;
	- an answer is marked as incorrect (kept in the red box) is none of the previous cases happen.

### Execution:
Maven is used to build the project: https://maven.apache.org/
- Project build, inside the root of the project folder, where the pom.xml is: 
		```mvn clean install``` --> builds project and executes the tests

- Program execution:
  1. Run the main program AnkiGame from the Run options of your IDE.
  2. Or navigate to the created jar inside the target folder and execute the following command:
  ```java -cp anki-1.0-SNAPSHOT.jar com.anki.AnkiGame ../resources/cards.txt```
