package com.obal.dominos;

/**
 * Representation of a move, ie which domino to put and where
 */
public class Move {

    public Domino domino;
    public enum Side{
        LEFT,
        RIGHT
    }

    public Side side;
    
    /**
     * Instantiate a Move with the given domino and side
     * @param d domino of the move
     * @param s side of the move
     */
    public Move(Domino d, Side s){
        side = s;
        domino = d;
    }
    
}