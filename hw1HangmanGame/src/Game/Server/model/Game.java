package Game.Server.model;

import java.util.Scanner;

public class Game {
    private String word; // Actual random word extracted
    private String gameWord;//includes hidden chars
    private String message; // message sent to user depending on what they enter
    private static String loseMsg = "You lose";
    private static String winMsg = "You win!";
    private static String hiddenChar = "_";
    private int numAttempts, score = 0; // Score and number of attempts sent to client after each entry
    private boolean wonGame;

    String startGame() {
        word = Word.randomWord();
        numAttempts = word.length();
        message = "Hello, welcome to Hangman!";
        wonGame = false;

        StringBuilder sb = new StringBuilder();

        for (char c: word.toCharArray()) {
            sb.append(hiddenChar);
        }

        gameWord = sb.toString();

        System.out.println("Word is " + word);

        return createResponse();
    }

    String gameEntry(String input) {
        boolean correctGuess = false;
        wonGame = false;

        if (numAttempts != 0) {
            if (input.length() > 1) {
                if (input.equals(word)) {
                    wonGame = true;
                    gameWord = word;
                }
            } else if (input.length() == 1) {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (c == input.charAt(0)) {
                        sb.append(c);
                        correctGuess = true;
                    } else {
                        sb.append(gameWord.charAt(i));
                    }
                }

                gameWord = sb.toString();
                if (!gameWord.contains(hiddenChar))
                    wonGame = true;
            }

            if (!correctGuess) {
                message = "wrong";
                numAttempts--;
            }
            else{
                message = "correct";
            }
        }

        if (wonGame) {
            return winGame();
        } else if (numAttempts < 1) {
            return loseGame();
        }

        return createResponse();
    }

    String restart() {
        return startGame();
    }

    private String loseGame() {
        message = loseMsg;
        score+=-1;
        return createResponse();
    }

    private String winGame() {
        message = winMsg;
        score++;
        return createResponse();
    }

    private String createResponse() {
        String response;

        StringBuilder sb = new StringBuilder();

        sb.append(score);
        sb.append("/");
        sb.append(numAttempts);
        sb.append("/");
        sb.append(gameWord);
        sb.append("/");
        sb.append(message);
        sb.append("/");

        response = sb.toString();
        return response;
    }
}
