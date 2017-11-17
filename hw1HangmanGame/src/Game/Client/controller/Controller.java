package Game.Client.controller;

import Game.Client.net.ServerConnector;

import java.io.IOException;

public class Controller {
    private ServerConnector connector = new ServerConnector();

    public Controller (){
        connect();
    }
    // send command to server through server connector
    public void set(String str) throws IOException {
        connector.sendToServer(str);
    }

    public String get() throws IOException {
        return connector.receiveFromServer();
    }

    private void connect (){
        connector.connectToServer();
    }
}
