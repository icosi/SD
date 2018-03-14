package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    Scanner sc = new Scanner(System.in);

    public static int ante = 1;
    private int stackServer, stackClient;
    private Deck deck;
    private Card cardServer, cardClient;
    private int bote;
    private String turn;
    private String winner;


    public Game() {
        float r  = Math.round( Math.random());
        if (r > 0.5f) { this.turn = "C"; }
        else { this.turn = "S"; }
        this.stackClient = 100;
        this.stackServer = 100;
        this.deck = new Deck();
        deck.burnCard();
        this.cardServer = deck.getCard();
        this.cardClient = deck.getCard();
        this.bote = 0;
    }

    public static int getAnte() {
        return ante;
    }

    public void addBote() {
        this.bote += 1;
    }

    public void subBote() {
        this.bote -= 1;
    }

    public static void setAnte(int ante) {
        Game.ante = ante;
    }

    public int getStackServer() {
        return stackServer;
    }

    public void setStackServer(int stackServer) {
        this.stackServer = stackServer;
    }

    public int getStackClient() {
        return stackClient;
    }

    public void setStackClient(int stackClient) {
        this.stackClient = stackClient;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Card getCardServer() {
        return cardServer;
    }

    public void setCardServer(Card cardServer) {
        this.cardServer = cardServer;
    }

    public Card getCardClient() {
        return this.cardClient;
    }

    public void setCardClient(Card cardClient) {
        this.cardClient = cardClient;
    }

    public int getBote() {
        return bote;
    }

    public void setBote(int bote) {
        this.bote = bote;
    }

    public String calculateWinner(Card cardClient, Card cardServer) {
        if (cardClient.value() < cardServer.value()) {
            return "S";
        } return "C";
    }

    public void setWinner(String s) {
        this.winner = s;
    }

    public String getWinner() {
        return this.winner;
    }

    public String getTurn() {
        return this.turn;
    }


}
