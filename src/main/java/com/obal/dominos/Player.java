package com.obal.dominos;
import java.util.Comparator;

public abstract class Player{
    Hand hand;

    public Player(Hand h) {
        hand = h;
    }

    public abstract Move playNextMove(Snake snake);

    public void removeDomino(Domino domino){
        hand.dominoes.remove(domino);
    }
    
    public static Comparator<Player> HandComparator = new Comparator<Player>(){
        public int compare(Player p1, Player p2){
            int sumPlayer = 0;
            for (Domino d:p1.hand.dominoes){
                sumPlayer += d.values[0] + d.values[1];
            }
            int sumCompared = 0;
            for (Domino d:p2.hand.dominoes){
                sumCompared += d.values[0] + d.values[1];
            }
            return sumPlayer - sumCompared;
    
        }
    };
}