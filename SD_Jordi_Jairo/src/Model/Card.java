/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jordi
 */
public class Card {
    private char code;

    public Card(char code) {
        this.code = code;
    }

    public char getCode() {
        return this.code;
    }

    public void setCode(char code) {
        this.code = code;
    }

    public int value() {
        int value;
        switch (this.code) {
            case 'J':
                value = 1;
                break;
            case 'Q':
                value = 2;
                break;
            case 'K':
                value = 3;
                break;
            default:
                value = 0;
        }
        return value;
    }
}
