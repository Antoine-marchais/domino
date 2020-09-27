package com.obal.dominos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Game {
    
    //CTS
    static final byte DOMINO_MAX = 6;
    static final byte DOMINO_MIN = 0;
    static final byte DRAW_SIZE = 7;
    private ArrayList<Player> players;
    private int player_index;
    private Snake snake;

    public Game(){
        // Build the deck
        LinkedList<Domino> deck = new LinkedList<>();
        for (int i = DOMINO_MIN; i <= DOMINO_MAX; i++) {
            for (int j = i; j <= DOMINO_MAX; j++) {
                int[] vl = {i, j};
                deck.addLast(new Domino(vl));
            }
        }
        // Draw hands
        Collections.shuffle(deck);
        Hand playerOneHand = new Hand();
        Hand playerTwoHand = new Hand();
        Hand playerThreeHand = new Hand();
        for (int i = 0; i < DRAW_SIZE; i++) {
            playerOneHand.addDomino(deck.getFirst());
            deck.removeFirst();
            playerTwoHand.addDomino(deck.getFirst());
            deck.removeFirst();
            playerThreeHand.addDomino(deck.getFirst());
            deck.removeFirst();
        }   

        players = new ArrayList<Player>();
        players.add(new HumanPlayer(playerOneHand));
        players.add(new HumanPlayer(playerTwoHand));
        players.add(new HumanPlayer(playerThreeHand));
        player_index = 0;
        snake = new Snake();
    }

    public void start(){
        boolean ending = false;
        while (!ending){
            nextTurn();
        }
    }

    private void nextTurn(){
        System.out.println(String.format("\nPlayer %s turn. Current snake : %s", player_index+1, snake));
        Player player = players.get(player_index);
        player_index = (player_index + 1)%3;
        boolean correct_move = false;
        while (!correct_move){
            try {
                Move next_move = player.playNextMove(snake);
                if (next_move.side == Move.Side.LEFT) snake.layLeftEnd(next_move.domino);
                else snake.layRightEnd(next_move.domino);
                player.removeDomino(next_move.domino);
                correct_move = true;
            } catch (InvalidMoveException e){
                System.out.println(String.format("Invalid move : %s", e.getMessage()));
            }
        }
    }
}