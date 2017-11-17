package Game.Server.model;

public class GameLogic {
    private String gameData = "default";
    private Boolean isGameStarted = false;
    private Game game;

    public String getGameData () {
        System.out.println("Server Sending word: " + gameData);
        return gameData;
    }

    public void setGameData (String input) {
        System.out.println("Server Received word: " + input);

        if (isGameStarted)
            switch (input) {
                case "Start Game":
                    this.gameData = "Game already in play";
                    break;
                case "Play Again":
                    restart();
                    break;
                case "End Game":
                    this.gameData = "Quitting Game";
                    break;
                default:
                    gameEntry(input);
                    break;
            }

        if (input.equals("Start Game") && !isGameStarted) {
            isGameStarted = true;
            startGame();
        }
    }

    private void startGame() {
        game = new Game();
        this.gameData = game.startGame();
    }

    private void restart() {
        this.gameData = game.restart();
    }

    private void gameEntry(String input) {
        this.gameData = game.gameEntry(input);
    }
}
