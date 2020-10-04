package com.obal.dominos;
import java.util.Comparator;

/**
 * Base class for the player
 */
public abstract class Player{
    Hand hand;

    /**
     * Instantiates a new player from a given hand
     * @param   hand    initial hand of the player
     */
    public Player(Hand h) {
        hand = h;
    }

    public abstract Move playNextMove(Snake snake);

    /**
     * remove domino from players hand
     * @param   domino  domino to remove from hand
     */
    public void removeDomino(Domino domino){
        hand.dominoes.remove(domino);
    }
    
    /**
     * Player comparator based on the total value of their hand
     */
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