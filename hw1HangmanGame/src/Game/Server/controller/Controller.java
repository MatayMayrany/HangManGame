package Game.Server.controller;

import Game.Server.model.Game;
import Game.Server.model.GameLogic;

public class Controller {
    private GameLogic game = new GameLogic();

    // send data to model or  objectgame
    public void setInput (String receivedData){
        game.setGameData(receivedData);
    }

    // get data from model
    public String getOutput (){
        return game.getGameData();
    }
}
