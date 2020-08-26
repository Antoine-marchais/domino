package com.obal.dominos;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

class Domino {
    byte[] values;

    public Domino(byte[] vl) {
        values = vl;
    }

    void printValue() {
        System.out.println(Arrays.toString(values));
    }
}

class Hand {
    LinkedList<Domino> dominoes = new LinkedList<>();

    void printHand() {
        for (Domino domino : dominoes)
            System.out.print(Arrays.toString(domino.values));
        System.out.println();
    }

    void addDomino(Domino domino) {
        dominoes.addFirst(domino);
    }
    boolean isInHand(Domino domino) {
        return dominoes.contains(domino);
    }
    void dropDomino(Domino domino) {
        if (isInHand(domino))
            dominoes.removeFirstOccurrence(domino);
        //TODO : ADD ERROR HANDLING
    }
}

class Snake {
    // TODO : Find a better name for dominoes on the board haha
    LinkedList<Domino> dominoes = new LinkedList<>();

    void printSnake() {
        for (Domino domino : dominoes)
            System.out.print(Arrays.toString(domino.values));
        System.out.println();
    }

    byte getRightValue() {
        return dominoes.getLast().values[1];
    }

    byte getLeftValue() {
        return dominoes.getFirst().values[0];
    }

    void layRightEnd(Domino domino) {

    }

    void layLeftEnd(Domino domino) {

    }

}
public class App 
{
    //CTS
    static final byte DOMINO_MAX = 6;
    static final byte DOMINO_MIN = 0;
    static final byte DRAW_SIZE = 7;

    public static void main( String[] args )
    {
        // Build the deck
        LinkedList<Domino> deck = new LinkedList<>();
        for (byte i = DOMINO_MIN; i <= DOMINO_MAX; i++) {
            for (byte j = i; j <= DOMINO_MAX; j++) {
                byte[] vl = {i, j};
                deck.addLast(new Domino(vl));
            }
        }
        // Draw hands
        Collections.shuffle(deck);
        Hand playerOneHand = new Hand();
        Hand playerTwoHand = new Hand();
        Hand playerThreeHand = new Hand();
        for (int i = 0; i < DRAW_SIZE; i++) {
            playerOneHand.addDomino(deck.getFirst());
            deck.removeFirst();
            playerTwoHand.addDomino(deck.getFirst());
            deck.removeFirst();
            playerThreeHand.addDomino(deck.getFirst());
            deck.removeFirst();
        }
        playerOneHand.printHand();
        System.out.println("\nDeck size : " + deck.size());
    }

}
