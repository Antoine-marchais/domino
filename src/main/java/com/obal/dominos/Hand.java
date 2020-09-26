package com.obal.dominos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class Hand {
    LinkedList<Domino> dominoes = new LinkedList<>();

    void printHand() {
        for (Domino domino : dominoes)
            System.out.print(Arrays.toString(domino.values));
        System.out.println();
    }

    void addDomino(Domino domino) {
        dominoes.addFirst(domino);
    }
    boolean isInHand(Domino domino) {
        return dominoes.contains(domino);
    }
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