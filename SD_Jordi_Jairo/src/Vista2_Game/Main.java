package Vista2_Game;

import Controller.*;
import Model.ProtocolSupport;
import Model.Game;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        ServerSocket server = null;
        Socket newClient;
        ComUtils utils;
        Controller controller;
        Game game;
        ProtocolSupport protocol;
        int port;
        HashMap<Socket, Integer> hmap = new HashMap<>();
        int numThreads;

        game = new Game();
        protocol = new ProtocolSupport();
        port = Integer.parseInt(args[1]);
        numThreads = 0;
    }
}
