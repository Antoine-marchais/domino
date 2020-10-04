package com.obal.dominos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Represents the set of dominos a player posesses
 */
class Hand {
    LinkedList<Domino> dominoes = new LinkedList<>();

    /**
     * Add a domino in the hand
     * @param domino domino to add
     */
    void addDomino(Domino domino) {
        dominoes.addFirst(domino);
    }

    /**
     * Check wether the given domino is in the hand
     * @param   domino  domino to check hand against
     * @return          true if the domino is in hand
     */
    boolean isInHand(Domino domino) {
        return dominoes.contains(domino);
    }
    
    /**
     * Remove the domino from hand
     * @param   domino  domino to remove from hand
     */
    void dropDomino(Domino domino) {
        if (isInHand(domino))
            dominoes.removeFirstOccurrence(domino);
        //TODO : ADD ERROR HANDLING
    }

    @Override
    public String toString(){
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < dominoes.size(); i++){
            result.add(String.format("%s:%s", i+1, dominoes.get(i)));
        }
        return String.join("  ", result);
    }
}