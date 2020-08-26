package com.obal.dominos;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

class Domino {
    byte[] value;

    public Domino(byte[] vl) {
        value = vl;
    }

    void printValue() {
        System.out.println(Arrays.toString(value));
    }
}

class Hand {
    LinkedList<Domino> dominos = new LinkedList<>();

    void printHand() {
        for (Domino domino : dominos)
            System.out.print(Arrays.toString(domino.value));
        System.out.println();
    }

    void addDomino(Domino domino) {
        dominos.addFirst(domino);
    }
    boolean isInHand(Domino domino) {
        return dominos.contains(domino);
    }
    void dropDomino(Domino domino) {
        if (isInHand(domino))
            dominos.removeFirstOccurrence(domino);
        //TODO : ADD ERROR HANDLING
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
