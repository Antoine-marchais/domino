package com.obal.dominos;

public class InvalidMoveException extends Exception{
    
    private static final long serialVersionUID = 1L;

    public InvalidMoveException() {
        super();
    }

    public InvalidMoveException(String message){
        super(message);
    }
}