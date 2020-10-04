package com.obal.dominos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.UUID;
import java.util.stream.IntStream;

public class Game {
    
    //CTS
    static final byte DOMINO_MAX = 6;
    static final byte DOMINO_MIN = 0;
    static final byte DRAW_SIZE = 7;
    private ArrayList<Player> players;
    private int player_index;
    private Snake snake;
    private enum TurnResult{
        PASS,
        PLAY,
        WIN
    }

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
            playerOneHand.addDomino(deck.pop());
            playerTwoHand.addDomino(deck.pop());
            playerThreeHand.addDomino(deck.pop());
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
        int pass_count = 0;
        while (!ending){
            switch (nextTurn()){
                case PASS:
                    pass_count++;
                    if (pass_count == 3){
                        ending=true;
                    }
                    break;
                case WIN:
                    ending = true;
                    break;
                case PLAY:
                    pass_count = 0;
                    break;
            }
        }
        System.out.println("\nGame over !");
        int winningPlayerIndex = checkScores();
        System.out.println(String.format("Player %s won", winningPlayerIndex));
    }

    public int checkScores(){
        int[] sortedIndices = IntStream
            .range(0, players.size())
            .boxed().sorted((i, j) -> Player.HandComparator.compare(players.get(i), players.get(j)))
            .mapToInt(ele -> ele).toArray();
        return sortedIndices[0]; 
    }

    private boolean canPlay(Player player){
        for (Domino domino : player.hand.dominoes) {
            if (Arrays.stream(domino.values).anyMatch(v -> v == snake.getLeftValue()))
                return true;
            if (Arrays.stream(domino.values).anyMatch(v -> v == snake.getRightValue()))
                return true;
        }
        return false;
    }

    private TurnResult nextTurn(){
        System.out.println(String.format("\nPlayer %s turn. Current snake : %s", player_index+1, snake));
        Player player = players.get(player_index);
        player_index = (player_index + 1)%3;
        boolean correct_move = false;
        if (!canPlay(player)){
            System.out.println("PASS");
            return TurnResult.PASS;
        }
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
        if (player.hand.dominoes.size() == 0){
            return TurnResult.WIN;
        } else {
            return TurnResult.PLAY;
        }
    }
}