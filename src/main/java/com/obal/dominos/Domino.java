package com.obal.dominos;

import java.util.Arrays;

class Domino {
    int[] values;

    public Domino(int[] vl) {
        values = vl;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}