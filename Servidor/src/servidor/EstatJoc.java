package servidor;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EstatJoc {

    private int monedesServidor;
    private int monedesClient;
    private int bC;
    private int bS;
    private char turn;
    private boolean foldClient;
    private boolean foldServidor;
    private char cardClient;
    private char cC;
    private char cardServidor;
    private ArrayList cards;

    public EstatJoc(){

    }

    public EstatJoc(int monedesServidor, int monedesClient){
        this.monedesClient = monedesClient;
        this.monedesServidor = monedesServidor;
        this.cards = new ArrayList<Character>() {
            {
                add('J');
                add('Q');
                add('K');
            }
        };
    }

    public void setCardsCS(){
        int random = ThreadLocalRandom.current().nextInt(0,3);
        char cC = (char) this.cards.get(random);
        this.cards.remove(random);
        // Printem la carta del client
        System.out.println("Carta: "+cC);
        random = ThreadLocalRandom.current().nextInt(0,2);
        char cS = (char) this.cards.get(random);
        this.cards.remove(random);
    }
    
    
    public char getCardClient(){
        return cC;
    }

    public int getMonedesServidor() {
        return monedesServidor;
    }

    public void setMonedesServidor(int monedesServidor) {
        this.monedesServidor = monedesServidor;
    }

    public int getMonedesClient() {
        return monedesClient;
    }

    public void setMonedesClient(int monedesClient) {
        this.monedesClient = monedesClient;
    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }

    public int getbC() {
        return bC;
    }

    public void setbC(int bC) {
        this.bC = this.bC;
    }

    public int getbS() {
        return bS;
    }

    public void setbS(int bS) {
        this.bS = this.bS;
    }

    public boolean isFoldClient() {
        return foldClient;
    }

    public void setFoldClient(boolean foldClient) {
        this.foldClient = foldClient;
    }

    public boolean isFoldServidor() {
        return foldServidor;
    }

    public void setFoldServidor(boolean foldServidor) {
        this.foldServidor = foldServidor;
    }
}
