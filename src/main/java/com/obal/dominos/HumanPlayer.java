package com.obal.dominos;

import java.util.Scanner;

/**
 * Implementation of a Player with a CLI to input next move
 */
public class HumanPlayer extends Player {

    private Scanner input;
    
    /**
     * Instanciates a Human player with a new hand and an input scanner
     * @param h hand to use for the player
     */
    public HumanPlayer(Hand h) {
        super(h);
        input = new Scanner(System.in);
    }

    /**
     * prompts the player for his next move, displaying the current snake and current hand
     * @param snake the current snake of the game
     * @return      the move chosen by the player
     */
    @Override
    public Move playNextMove(Snake snake){
        int handSize = hand.dominoes.size();
        System.out.println(String.format("Your hand : %s", hand));
        System.out.print(String.format("Which domino do you want to place (1-%s) ? ", handSize));
        boolean domino_validation = false;
        Domino domino = null;

        // We ask for the domino index, with validation
        while (!domino_validation){
            try {
                int domino_number = Integer.parseInt(input.nextLine());
                if (domino_number > hand.dominoes.size()) throw new Exception();
                domino = hand.dominoes.get(domino_number-1);
                domino_validation = true;
            } catch (Exception e){
                e.printStackTrace();
                System.out.print("Please input the index of one of your dominoes : ");
            }
        }

        System.out.print("On which side (L/R) ? ");
        String side = "";
        // We ask for the domino side, with validation
        boolean side_validation = false;
        while (!side_validation){
            try {
                side = input.nextLine();
                if (!side.equals("L") && !side.equals("R")) throw new Exception();
                side_validation = true;
            } catch (Exception e){
                System.out.print("You can only input characters L and R : ");
            }
        }
        Move result = side.equals("L") ? new Move(domino, Move.Side.LEFT) : new Move(domino, Move.Side.RIGHT);
        return result;
    }
}