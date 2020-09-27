package com.obal.dominos;

import java.util.Scanner;

public class HumanPlayer extends Player {
    
    public HumanPlayer(Hand h) {
        super(h);
    }

    @Override
    public Move playNextMove(Snake snake){
        System.out.println(String.format("Current Snake : %s \n", snake));
        System.out.println(String.format("Your hand : %s \n", hand));;
        System.out.println("Which domino do you want to place ? (1-7)");
        Scanner input = new Scanner(System.in);
        boolean domino_validation = false;
        Domino domino = null;
        while (!domino_validation){
            try {
                int domino_number = Integer.parseInt(input.nextLine());
                if (domino_number > hand.dominoes.size()) throw new Exception();
                domino = hand.dominoes.get(domino_number-1);
                domino_validation = true;
            } catch (Exception e){
                System.out.println("Please input the index of one of your dominoes");
            }
        }

        System.out.println("On which side ? (L/R");
        String side = "";
        boolean side_validation = false;
        while (!side_validation){
            try {
                side = input.nextLine();
                if (side != "L" && side != "R") throw new Exception();
                domino_validation = true;
            } catch (Exception e){
                System.out.println("You can only input characters L and R");
            }
        }
        input.close();
        Move result = side == "L" ? new Move(domino, Move.Side.LEFT) : new Move(domino, Move.Side.RIGHT);
        return result;
    }
}