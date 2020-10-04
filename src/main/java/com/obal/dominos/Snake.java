package com.obal.dominos;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Representation of the dominoes laid on the board
 */
class Snake {
    // TODO : Find a better name for dominoes on the board haha
    LinkedList<Domino> dominoes;
    int rightValue;
    int leftValue;

    /**
     * Instantiates a new empty Snake
     */
    public Snake(){
        dominoes = new LinkedList<Domino>();
    }

    int getRightValue() {
        return rightValue;
    }

    int getLeftValue() {
        return leftValue;
    }

    /**
     * Try to place a new domino on the right hand of the snake
     * @param domino domino to place on the end of the snake
     * @throws InvalidMoveException raises an exception if the domino does not match that end
     */
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

    /**
     * Try to place a new domino on the left hand of the snake
     * @param domino domino to place on the end of the snake
     * @throws InvalidMoveException raises an exception if the domino does not match that end
     */
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