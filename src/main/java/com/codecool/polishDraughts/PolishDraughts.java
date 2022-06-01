package com.codecool.polishDraughts;

import java.util.Scanner;

public class PolishDraughts {

    public static void main (String[] args) {
        System.out.println("Please define the board size(Between 10 and 20):");
        int size = getBoardSize();
        while (!isValidBoardSize(size)) {
            System.out.println("Invalid board size! Try again!");
            getBoardSize();
        }
        Game newGame = new Game(size);
        newGame.start();
    }

    private static boolean isValidBoardSize(int n) {
        return (n >= 10 && n <=20);
    }

    private static int getBoardSize() {
        Scanner input = new Scanner(System.in);
        try {
            int size = input.nextInt();
            return size;
        }catch (Exception e) {
            return -1;
        }

    }
}
