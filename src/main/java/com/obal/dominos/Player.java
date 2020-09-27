package com.obal.dominos;

public abstract class Player {
    Hand hand;

    public Player(Hand h) {
        hand = h;
    }

    public abstract Move playNextMove(Snake snake);
}