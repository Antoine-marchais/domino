package com.obal.dominos;

public abstract class Player {
    Hand hand;

    public Player(Hand h) {
        hand = h;
    }

    public abstract Move playNextMove(Snake snake);

    public void removeDomino(Domino domino){
        hand.dominoes.remove(domino);
    }
}