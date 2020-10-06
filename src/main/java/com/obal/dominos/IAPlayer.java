package com.obal.dominos;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class IAPlayer extends Player {

    public IAPlayer(Hand h) {
        super(h);
    }

    @Override
    public Move playNextMove(Snake snake) {
        ArrayList<Move> possibleMoves = getPossibleMoves(snake);
        Move maxMove = possibleMoves.get(0);
        for (Move move: possibleMoves){
            if (IntStream.of(move.domino.values).sum() > IntStream.of(maxMove.domino.values).sum()){
                maxMove = move;
            }
        }
        return maxMove;
    }

    private ArrayList<Move> getPossibleMoves(Snake snake){
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        for (Domino domino: hand.dominoes){
            if (domino.values[0] == snake.rightValue || domino.values[1] == snake.rightValue){
                possibleMoves.add(new Move(domino, Move.Side.RIGHT));
            } else if (domino.values[0] == snake.leftValue || domino.values[1] == snake.leftValue){
                possibleMoves.add(new Move(domino, Move.Side.LEFT));
            }
        }
        return possibleMoves;
    }
}