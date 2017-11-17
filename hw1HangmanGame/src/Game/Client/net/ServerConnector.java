package Game.Client.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerConnector {
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private static InetAddress host;
    private static final int PORT_NO = 8080;

    public void connectToServer (){
        try{
            host = InetAddress.getLocalHost(); // gets IP address of machine
        }
        catch (UnknownHostException uhEX){
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        try {
            socket = new Socket(host, PORT_NO); // connect to service
        }
        catch (IOException ioe){
            System.out.println("couldn't connect to host");
        }
    }
    //
    public void sendToServer (String sentData) throws IOException {
        output = new PrintWriter(socket.getOutputStream(), true); // writing to other side of socket or server
        // if wanting to disconnect first send to server so it knows then close socket
        if(sentData.contains("disconnect")){
            disconnect();
        }
        output.println(sentData);
    }

    public String receiveFromServer () throws IOException {
        input = new Scanner(socket.getInputStream());
        return input.nextLine();
    }
    public void disconnect(){
        System.out.println("disconnecting");
        try{
            socket.close();
            System.exit(0);
        }
        catch (IOException ioe){
            System.out.println(ioe);
        }

    }

}
