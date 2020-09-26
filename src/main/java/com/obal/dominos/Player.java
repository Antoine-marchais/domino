package com.obal.dominos;

public class Player {
    Hand hand;

    public Player(Hand h) {
        hand = h;
    }

    public Domino playNextMove(Snake snake){
        //TODO
        return new Domino(new int[]{0,0});
    }
}