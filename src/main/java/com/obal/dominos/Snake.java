package com.obal.dominos;

import java.util.ArrayList;
import java.util.LinkedList;

class Snake {
    // TODO : Find a better name for dominoes on the board haha
    LinkedList<Domino> dominoes;
    int rightValue;
    int leftValue;

    public Snake(){
        dominoes = new LinkedList<Domino>();
    }

    int getRightValue() {
        return rightValue;
    }

    int getLeftValue() {
        return leftValue;
    }

    void layRightEnd(Domino domino) throws InvalidMoveException{
        if (dominoes.size() == 0){
            dominoes.addLast(domino);
            rightValue = domino.values[1];
            leftValue = domino.values[0];
        } else {
            if (domino.values[0] == rightValue || domino.values[1] == rightValue) {
                dominoes.addLast(domino);
                rightValue = domino.values[0] == rightValue ? domino.values[1] : domino.values[0];
            } else {
                throw new InvalidMoveException("Domino does not match right end of the snake");
            }
        }
    }

    void layLeftEnd(Domino domino) throws InvalidMoveException {
        if (dominoes.size() == 0){
            dominoes.addFirst(domino);
            rightValue = domino.values[1];
            leftValue = domino.values[0];
        } else {
            if (domino.values[0] == leftValue || domino.values[1] == leftValue) {
                dominoes.addFirst(domino);
                leftValue = domino.values[0] == leftValue ? domino.values[1] : domino.values[0];
            } else {
                throw new InvalidMoveException("Domino does not match right end of the snake");
            }
        }
    }

    @Override
    public String toString(){
        ArrayList<String> result = new ArrayList<String>();
        for (Domino domino: dominoes){
            result.add(domino.toString());
        }
        return String.format("(%s) %s (%s)", leftValue, String.join("", result), rightValue);
    }

}