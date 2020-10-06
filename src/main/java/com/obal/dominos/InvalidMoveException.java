package com.obal.dominos;

/**
 * Exception Raised in case of an invalid move
 */
public class InvalidMoveException extends Exception{
    
    private static final long serialVersionUID = 1L;

    public InvalidMoveException() {
        super();
    }

    public InvalidMoveException(String message){
        super(message);
    }
}