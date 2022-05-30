package com.codecool.polishDraughts;

public class Board {
    private Pawn[][] board;

    public Board(int n) {
        //check if n between 10 and 20
        this.board = createBoard(n);


    }

    private Pawn[][] createBoard(int n) {
        Pawn[][] result = new Pawn[n][n];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                if (i % 2 == 0 && j % 2 != 0) {
                    result[i][j] = new Pawn("black", i, j);
                } else if (i % 2 != 0 && j % 2 == 0) {
                    result[i][j] = new Pawn("black", i, j);
                } else {
                    result[i][j] = null;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].toString());
                } else {
                    System.out.print(" .. ");
                }
            }
            System.out.println();
        }
        return result;
    }

    public static void removePawn() {

    }

    public void movePawn() {

    }


}
