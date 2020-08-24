package com.obal.dominos;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name");
        String name = scan.nextLine();
        System.out.println("Hello Mr "+name);
    }
}
