package com.obal.dominos;

public class Move {

    public Domino domino;
    public enum Side{
        LEFT,
        RIGHT
    }

    public Side side;
    
    public Move(Domino d, Side s){
        side = s;
        domino = d;
    }
    
}