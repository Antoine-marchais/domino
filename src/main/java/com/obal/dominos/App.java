package com.obal.dominos;

import java.util.Collections;
import java.util.LinkedList;


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
        for (int i = DOMINO_MIN; i <= DOMINO_MAX; i++) {
            for (int j = i; j <= DOMINO_MAX; j++) {
                int[] vl = {i, j};
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
        System.out.println(playerOneHand);;
        System.out.println("\nDeck size : " + deck.size());
    }

}
