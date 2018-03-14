package Controller;

import Model.Card;
import Model.Game;
import Model.ProtocolSupport;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Controller {

    private ComUtils utils;
    private Game game;
    private ProtocolSupport protocolSupport;
    private int id;
    private FileWriter output;
    private PrintWriter pw;

    public Controller(ComUtils utils, ProtocolSupport protocolSupport, Game game, int numT) throws IOException {
        this.utils = utils;
        this.game = game;
        this.protocolSupport = protocolSupport;
        this.output = new FileWriter("Server" + numT + ".log");
        this.pw = new PrintWriter(this.output);
    }

    public void getId() throws IOException {
        this.id = protocolSupport.receive_UID(utils);
    }

    public void startGame() throws IOException {
        System.out.println("Waiting client ...");
        boolean end = false;
        String cmd_received = "";

        getId();
        System.out.println(this.id);

        cmd_received = utils.read_command3();

        switch (cmd_received) {
            case "PLY":
                System.out.println("Game started");

                protocolSupport.send_STK_TURN(utils, game);
                System.out.println("Dealer is:" + game.getTurn());

                protocolSupport.receive_BET(utils);
                utils.write_bet();
                game.setStackClient(game.getStackClient() - 1);
                game.setStackServer(game.getStackServer() - 1);
                game.setBote(2);

                protocolSupport.send_CRD(utils, game.getCardClient());

                while (!end) {
                    cmd_received = utils.read_command3();
                    if (game.getTurn() == "C") {
                        if (cmd_received == "BET") {
                            game.setStackClient(game.getStackClient() - 1);
                            game.addBote();
                            String[] s = {"FOLD", "CALL"};
                            Random ran = new Random();
                            String s_ran = s[ran.nextInt(s.length)];
                            if (s_ran == "FOLD") {
                                protocolSupport.send_WIN(utils,"C");
                                game.setStackClient(game.getStackClient() + game.getBote());
                                game.setBote(0);
                                end = true;
                                continue;
                            }
                            else {
                                utils.write_call();
                                game.setCardClient(new Card(protocolSupport.receive_SHW(utils).charAt(0)));
                                protocolSupport.send_CRD(utils, game.getCardServer());
                                String winner = game.calculateWinner(game.getCardClient(), game.getCardServer());
                                protocolSupport.send_WIN(utils, winner);
                                game.setStackClient(game.getStackClient() + game.getBote());
                                game.setBote(0);
                                end = true;
                                continue;

                            }
                        }
                        else if (cmd_received == "CHK") {
                            String[] s = {"CHECK", "BET"};
                            Random ran = new Random();
                            String s_ran = s[ran.nextInt(s.length)];
                            if (s_ran == "CHECK") {
                                utils.write_check();
                                game.setCardClient(new Card(protocolSupport.receive_SHW(utils).charAt(0)));
                                protocolSupport.send_CRD(utils, game.getCardServer());
                                String winner = game.calculateWinner(game.getCardClient(), game.getCardServer());
                                protocolSupport.send_WIN(utils, winner);
                                game.setStackClient(game.getStackClient() + game.getBote());
                                game.setBote(0);
                                end = true;
                                continue;
                            }
                            else {
                                utils.write_bet();
                                game.setStackServer(game.getStackServer() - 1);
                                game.addBote();
                                cmd_received = utils.read_command3();
                                if (cmd_received == "CAL") {
                                    game.setCardClient(new Card(protocolSupport.receive_SHW(utils).charAt(0)));
                                    protocolSupport.send_CRD(utils, game.getCardServer());
                                    String winner = game.calculateWinner(game.getCardClient(), game.getCardServer());
                                    protocolSupport.send_WIN(utils, winner);
                                    game.setStackClient(game.getStackClient() + game.getBote());
                                    game.setBote(0);
                                    end = true;
                                    continue;
                                }
                                else if (cmd_received == "FLD") {
                                    protocolSupport.send_WIN(utils,"S");
                                    game.setStackServer(game.getStackServer() + game.getBote());
                                    game.setBote(0);
                                    end = true;
                                    continue;
                                }
                            }
                        }
                    } else {
                        String[] s = {"CHECK", "BET"};
                        Random ran = new Random();
                        String s_ran = s[ran.nextInt(s.length)];
                        if (s_ran == "CHECK") {
                            utils.write_check();
                            cmd_received = utils.read_command3();
                            if (cmd_received == "CHK") {
                                game.setCardClient(new Card(protocolSupport.receive_SHW(utils).charAt(0)));
                                protocolSupport.send_CRD(utils, game.getCardServer());
                                String winner = game.calculateWinner(game.getCardClient(), game.getCardServer());
                                protocolSupport.send_WIN(utils, winner);
                                game.setStackClient(game.getStackClient() + game.getBote());
                                game.setBote(0);
                                end = true;
                                continue;
                            }
                            if (cmd_received == "BET") {
                                String[] s2 = {"FOLD", "CALL"};
                                Random ran2 = new Random();
                                String s_ran2 = s2[ran.nextInt(s2.length)];
                                if (s_ran2 == "FOLD") {
                                    protocolSupport.send_WIN(utils,"C");
                                    game.setStackClient(game.getStackClient() + game.getBote());
                                    game.setBote(0);
                                    end = true;
                                    continue;
                                }
                                if (s_ran2 == "CALL") {
                                    utils.write_call();
                                    game.setCardClient(new Card(protocolSupport.receive_SHW(utils).charAt(0)));
                                    protocolSupport.send_CRD(utils, game.getCardServer());
                                    String winner = game.calculateWinner(game.getCardClient(), game.getCardServer());
                                    protocolSupport.send_WIN(utils, winner);
                                    game.setStackClient(game.getStackClient() + game.getBote());
                                    game.setBote(0);
                                    end = true;
                                    continue;
                                }
                            }

                        }
                        else {
                            utils.write_bet();
                            cmd_received = utils.read_command3();
                            if (cmd_received == "FLD") {
                                protocolSupport.send_WIN(utils,"S");
                                game.setStackClient(game.getStackClient() + game.getBote());
                                game.setBote(0);
                                end = true;
                                continue;
                            }
                            else if (cmd_received == "CAL") {
                                game.setCardClient(new Card(protocolSupport.receive_SHW(utils).charAt(0)));
                                protocolSupport.send_CRD(utils, game.getCardServer());
                                String winner = game.calculateWinner(game.getCardClient(), game.getCardServer());
                                protocolSupport.send_WIN(utils, winner);
                                game.setStackClient(game.getStackClient() + game.getBote());
                                game.setBote(0);
                                end = true;
                                continue;
                            }

                        }
                    }
                }
                break;

            case "STP":
                break;
        }
        if (end) {
            startGame();
        }
    }

    public void startGameOptimal() throws IOException {

    }
}


