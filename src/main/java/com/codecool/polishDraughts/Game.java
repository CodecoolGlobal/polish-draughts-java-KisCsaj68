package com.codecool.polishDraughts;

import org.w3c.dom.ls.LSOutput;

public class Game {
    private Board gameBoard = new Board(10);
    private Player playerOne = new Player("black");
    private Player playerTwo = new Player("white");

    public void start() {
        playRound(playerOne);
        playRound(playerTwo);
        this.gameBoard.toString();
    }

    public void playRound(Player player) {
        this.gameBoard.toString();
        int[] move = getMovePawnFrom(player);
        while(!checkPlayerAndPawnColor(player, move[0], move[1])) {
            move = getMovePawnFrom(player);
        }

         //System.out.println(tryToMakeMove(player, rowFrom, columnFrom, rowTo, columnTo));

        //this.gameBoard.removePawn(row, column);
    }

    public int[] getMovePawnFrom(Player player) {
        int[] result = new int[2];
        int rowFrom = player.getUserInput("Row from move: " + player.getColor());
        int columnFrom = player.getUserInput("Column from move: " + player.getColor());

        result[0] = rowFrom;
        result[1] = columnFrom;

        return result;
    }

    public int[] getMovePawnTo(Player player) {
        int[] result = new int[2];
        int rowTo = player.getUserInput("Row to move: " + player.getColor());
        int columnTo = player.getUserInput("Column to move: " + player.getColor());

        result[0] = rowTo;
        result[1] = columnTo;

        return result;
    }

    public boolean tryToMakeMove(Player player, int rowFrom, int columnFrom, int rowTo, int columnTo) {
        return checkPlayerAndPawnColor(player, rowFrom, rowTo);
    }

    public boolean checkPlayerAndPawnColor(Player player, int row, int column) {
        Pawn[][] board = this.gameBoard.getBoard();
        if(board[row][column] == null) {
            return false;
        }
        String playerColor = player.getColor();
        String pawnColor = board[row][column].getColor();

        return playerColor.equals(pawnColor);
    }

    public static boolean checkForWinner() {
        return true;
    }

}
