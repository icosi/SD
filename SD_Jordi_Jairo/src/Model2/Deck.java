package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>(3);
        Card c1 = new Card('J');
        Card c2 = new Card('Q');
        Card c3 = new Card('K');
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        Collections.shuffle(cards);
    }

    public Card getCard() {
        Card c;
        c = this.cards.get(0);
        this.cards.remove(0);
        return c;
    }

    public void burnCard() {
        this.cards.remove(0);
    }

}
