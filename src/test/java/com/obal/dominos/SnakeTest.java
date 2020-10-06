package com.obal.dominos;
import org.junit.Test;
import org.junit.Assert;

public class SnakeTest {

    Domino firstDomino = new Domino(new int[]{3,4});
    Domino secondGoodDomino = new Domino(new int[]{5,4});
    Domino secondBadDomino = new Domino(new int[]{5,2});

    
    @Test
    public void testCreate() {
        Domino firstDomino = new Domino(new int[]{3,4});
        Snake snake = new Snake();
        try {
            snake.layLeftEnd(firstDomino);
            Assert.assertEquals(snake.dominoes.getFirst(), firstDomino);
        } catch (InvalidMoveException e){
            Assert.fail("Shouldn't have raised an exception");
        }
    }

    /**
     * Checks that the addition of a correct domino updates the snake accordingly
     */
    @Test
    public void testAddGood() {
        Snake snake = new Snake();
        try {
            snake.layLeftEnd(firstDomino);
            snake.layRightEnd(secondGoodDomino);
            Assert.assertEquals(snake.leftValue, 3);
            Assert.assertEquals(snake.rightValue, 5);
        } catch (InvalidMoveException e){
            Assert.fail("Shouldn't have raise an exception");
        }
    }

    /**
     * Checks that the addition of a wrong domino raises an exception
     */
    @Test
    public void testAddBad() {
        Snake snake = new Snake();
        try {
            snake.layLeftEnd(firstDomino);
            snake.layRightEnd(secondBadDomino);
            Assert.fail("faulty placement should have raised exception");
        } catch (InvalidMoveException e) {
            
        }
    }
}