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
            for (int k = n - 4; k < n; k++) {
                for (int l = 0; l < n; l++) {
                    if (k % 2 == 0 && l % 2 != 0) {
                        result[k][l] = new Pawn("white", k, l);
                    } else if (k % 2 != 0 && l % 2 == 0) {
                        result[k][l] = new Pawn("white", k, l);
                    } else {
                        result[k][l] = null;
                    }
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
                    System.out.print(" " + board[i][j].toString() + " ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        return result;
    }

    public void removePawn(int x, int y) {
        board[x][y] = null;
    }

    public void movePawn(int x, int y, int nextX, int nextY) {
        String color = board[x][y].getColor();
        removePawn(x,y);
        board[nextX][nextY] = new Pawn(color, nextX, nextY);

    }


}
