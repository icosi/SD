package Controller;

import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

    private Controller controller;
    private Socket socket;
    private int mode;

    public ServerThread(String threadName, Controller controller, int mode, Socket socket) {
        /* We initialize the Thread */
        super(threadName);
        this.controller = controller;
        this.socket = socket;
        this.mode = mode;
        //start();
    }
    public void run() {
        try {
            switch (mode) {
                case 0:
                    controller.startGame();
                case 1:
                    controller.startGameOptimal();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
