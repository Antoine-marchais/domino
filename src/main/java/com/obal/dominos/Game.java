package com.obal.dominos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Brings players and snake together and implement the game logic
 */
public class Game {
    
    //CTS
    static final byte DOMINO_MAX = 6;
    static final byte DOMINO_MIN = 0;
    static final byte DRAW_SIZE = 7;
    private ArrayList<Player> players;
    private int player_index = 0;
    private Snake snake;
    public enum TurnResult{
        PASS,
        PLAY,
        WIN
    }
    private int[] scores = {0,0,0};

    /**
     * Instantiates a new random Game
     */
    public Game(){
        int seed = new Random().nextInt();
        setupGame(seed);
    }

    /**
     * Instantiates a new game from the given seed
     * @param seed seed to use for the hand distribution
     */
    public Game(int seed){
        setupGame(seed);
    }


    /**
     * sets up the game with the the given seed
     * @param seed
     */
    private void setupGame(int seed){
        LinkedList<Domino> deck = buildDeck();

        Hand playerOneHand = new Hand();
        Hand playerTwoHand = new Hand();
        Hand playerThreeHand = new Hand();
        players = new ArrayList<Player>();
        players.add(new IAPlayer(playerOneHand));
        players.add(new IAPlayer(playerTwoHand));
        players.add(new IAPlayer(playerThreeHand));
        snake = new Snake();
    }

    /**
     * Build the domino deck
     * @return LinkedList<Domino>
     */
    private LinkedList<Domino> buildDeck(){
        LinkedList<Domino> deck = new LinkedList<>();
        for (int i = DOMINO_MIN; i <= DOMINO_MAX; i++) {
            for (int j = i; j <= DOMINO_MAX; j++) {
                int[] vl = {i, j};
                deck.addLast(new Domino(vl));
            }
        }
        return deck;
    }

    /**
     * Draw hand for all players
     * @param deck LinkedList<Domino>
     */
    private void drawHands(LinkedList<Domino> deck) {
        for (int i = 0; i < DRAW_SIZE; i++) {
            for (Player player : players) {
                player.hand.addDomino(deck.pop());
            }
        }
    }

    /**
     * Empty hands for all players.
     */
    private void dropHands() {
        for (Player player : players) {
            player.hand.dominoes.clear();
        }
    }


    /**
     * Find who starts the first round and force the first move.
     */
    private void openingMove() {
        int[] lookFor = {DOMINO_MAX, DOMINO_MAX};
        boolean foundFirst = false;
        while(!foundFirst) {
            for (Player player : players) {
                for (Domino domino : player.hand.dominoes) {
                    if (Arrays.equals(domino.values, lookFor)) {
                        System.out.println(String.format("%s is in the hand of the %dth player, he start.",
                                domino.toString(), player_index + 1));
                        foundFirst = true;
                        try {
                            snake.layLeftEnd(domino);
                        } catch (InvalidMoveException e){System.out.println("COCHON");}
                        player.removeDomino(domino);
                        player_index = (player_index + 1) % 3;
                        break;
                    }
                }
                if (foundFirst != true) {
                    player_index = (player_index + 1) % 3;
                } else {
                    break;
                }
            }
            if (foundFirst != true) {
                if (Arrays.stream(lookFor).anyMatch(i -> i == DOMINO_MIN)) {
                    // TODO : set a rule to pick the first player if none of the doubles were drawn
                    System.out.println("No double found, first player starts.");
                    foundFirst = true;
                }
                lookFor = Arrays.stream(lookFor).map(i -> i - 1).toArray();
            }
        }
    }

    /**
     * Start the game and prompts player for their turn while no end game condition is met
     */
    public void start() {
        boolean ending;
        int pass_count = 0;
        int winningPlayerIndex = 0;
        while (!IntStream.of(scores).anyMatch(x -> x == 3)) {
            ending = false;
            drawHands(buildDeck());
            if (Arrays.equals(scores, new int[]{0, 0, 0})) {
                // For the first round, find who starts and force the first move
                openingMove();
            }
            while (!ending) {
                switch (nextTurn()) {
                    case PASS:
                        // if three players pass, nobody can play and the game should end
                        pass_count++;
                        if (pass_count == 3) {
                            ending = true;
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
            winningPlayerIndex = checkScores();
            System.out.println(String.format("Player %d wins the round", winningPlayerIndex + 1));
            scores[winningPlayerIndex] += 1;
            snake.dominoes.clear();
            snake.leftValue = 0;
            snake.rightValue = 0;
            dropHands();
        }
        System.out.println(String.format("Player %s won the game : ",
                winningPlayerIndex + 1) + Arrays.toString(scores));
    }

    /**
     * Calculate player hand values and returns the player with the lowest hand value. and empty hand has value 0
     * @return index of the player with lowest hand value
     */
    public int checkScores(){
        int[] sortedIndices = IntStream
            .range(0, players.size())
            .boxed().sorted((i, j) -> Player.HandComparator.compare(players.get(i), players.get(j)))
            .mapToInt(ele -> ele).toArray();
        return sortedIndices[0]; 
    }

    /**
     * Checks whether a player has any domino he can add to the snake
     * @param player the player we are running the check for
     * @return true if the player can play
     */
    private boolean canPlay(Player player){
        if (snake.dominoes.size() == 0)
            return true;
        for (Domino domino : player.hand.dominoes) {
            if (Arrays.stream(domino.values).anyMatch(v -> v == snake.getLeftValue()))
                return true;
            if (Arrays.stream(domino.values).anyMatch(v -> v == snake.getRightValue()))
                return true;
        }
        return false;
    }

    /**
     * Checks the player can play, and if he can, prompts the player for next move, and updates the snake
     * @return PASS if no move is possible, WIN if the player finishes his hand, PLAY otherwise
     */
    private TurnResult nextTurn(){
        System.out.println(String.format("\nPlayer %s turn. Current snake : %s", player_index+1, snake));
        Player player = players.get(player_index);
        player_index = (player_index + 1)%3;
        boolean correct_move = false;

        //Pass if the player can't play
        if (!canPlay(player)){
            System.out.println("PASS");
            return TurnResult.PASS;
        }

        //prompts for the move and checks that the move can be layed on the snake
        while (!correct_move){
            try {
                Move next_move = player.playNextMove(snake);
                if (next_move.side == Move.Side.LEFT) snake.layLeftEnd(next_move.domino);
                else snake.layRightEnd(next_move.domino);
                player.removeDomino(next_move.domino);
                System.out.println(String.format("Player %s played %s", new int[]{3,1,2}[player_index], next_move));
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