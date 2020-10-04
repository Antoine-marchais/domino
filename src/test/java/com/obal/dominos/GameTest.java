package com.obal.dominos;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;

public class GameTest {

    public Game game;
    public ArrayList<Player> players;
    public Snake snake;
    public Field playersField;
    public Field snakeField;

    @Before
    public void createGame(){
        try{
            game = new Game(42);
            playersField = Game.class.getDeclaredField("players");
            playersField.setAccessible(true);
            players = (ArrayList<Player>)playersField.get(game);
            snakeField = Game.class.getDeclaredField("snake");
            snakeField.setAccessible(true);
            snake = (Snake)snakeField.get(game);
        } catch (Exception e){
            System.out.println("Exception in test");
        }
        //uncomment this to see the setup used in testing
        //System.out.println("snake : " + snake + "\nPlayers :");
        //for (Player p:players) System.out.println(p.hand);
    }

    @Test
    public void checkScoreTest(){
        int winningIndex = game.checkScores();
        Assert.assertEquals(winningIndex, 2);
    }

    @Test
    public void triplePassTest(){
        try{
            for (Player p: players){
                for (int i=0; i<6; i++) p.hand.dominoes.pop();
            }
            Snake snakeToSet = new Snake();
            snakeToSet.layLeftEnd(new Domino(new int[]{4, 4}));
            snakeField.set(game, snakeToSet);
            playersField.set(game, players);
            Method nextTurn = Game.class.getDeclaredMethod("nextTurn", null);
            nextTurn.setAccessible(true);
            for (int i=0;i<3;i++) Assert.assertEquals(nextTurn.invoke(game, null), Game.TurnResult.PASS);
        } catch (Exception e){
            Assert.fail("Should not have raised exception");
        }
    }

    public static void main(String[] args) {
        GameTest gt = new GameTest();
        gt.createGame();
        System.out.println("hey");
    }
}