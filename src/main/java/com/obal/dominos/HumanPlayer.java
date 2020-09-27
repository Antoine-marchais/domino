package com.obal.dominos;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner input;
    
    public HumanPlayer(Hand h) {
        super(h);
        input = new Scanner(System.in);
    }

    @Override
    public Move playNextMove(Snake snake){
        System.out.println(String.format("Your hand : %s", hand));;
        System.out.print("Which domino do you want to place (1-7) ? ");
        boolean domino_validation = false;
        Domino domino = null;
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
        boolean side_validation = false;
        while (!side_validation){
            try {
                side = input.nextLine();
                if (side.equals("L") && side.equals("R")) throw new Exception();
                side_validation = true;
            } catch (Exception e){
                System.out.print("You can only input characters L and R : ");
            }
        }
        Move result = side.equals("L") ? new Move(domino, Move.Side.LEFT) : new Move(domino, Move.Side.RIGHT);
        return result;
    }
}