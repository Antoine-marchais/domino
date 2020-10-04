package com.obal.dominos;

import java.util.Arrays;

/**
 * Representation of a domino
 */
class Domino {
    int[] values;

    /**
     * Instantiate a domino from the given values
     * @param vl values of each domino side
     */
    public Domino(int[] vl) {
        values = vl;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}