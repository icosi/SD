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

    
    public void setPlayers(){
        int random = ThreadLocalRandom.current().nextInt(0,2);

        // Si random = 1 --> Client es player1
        // Si ranodm = 2 --> Servidor es player1
        if (random == 1){
            this.turn = 'C';
        }
        else{
            this.turn = 'S';
        }
    }
    
    
    public void setCardsCS(Character ch){
        if (ch == 'C'){
            int random = ThreadLocalRandom.current().nextInt(0,3);
            this.cardClient = (char) this.cards.get(random);
            this.cards.remove(random);

            random = ThreadLocalRandom.current().nextInt(0,2);
            this.cardServidor = (char) this.cards.get(random);
            this.cards.remove(random);
        }
        if (ch == 'S'){
            int random = ThreadLocalRandom.current().nextInt(0,3);
            this.cardServidor = (char) this.cards.get(random);
            this.cards.remove(random);

            random = ThreadLocalRandom.current().nextInt(0,2);
            this.cardClient = (char) this.cards.get(random);
            this.cards.remove(random);
        }
    }
    
    
    public char getCardClient(){
        return cardClient;
    }
    
    public char getCardServidor(){
        return cardServidor;
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
