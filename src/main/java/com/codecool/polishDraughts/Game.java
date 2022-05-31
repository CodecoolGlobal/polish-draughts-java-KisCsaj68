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
        int rowFrom = player.getUserInput("Row from move: ");
        int columnFrom = player.getUserInput("Column from move: ");

        int rowTo = player.getUserInput("Row to move: ");
        int columnTo = player.getUserInput("Column to move: ");

         System.out.println(tryToMakeMove(player, rowFrom, columnFrom, rowTo, columnTo));

        //this.gameBoard.removePawn(row, column);
    }

    public boolean tryToMakeMove(Player player, int rowFrom, int columnFrom, int rowTo, int columnTo) {
        return checkPlayerAndPawnColor(player, rowFrom, rowTo);
    }

    public boolean checkPlayerAndPawnColor(Player player, int row, int column) {
        Pawn[][] board = this.gameBoard.getBoard();
        String playerColor = player.getColor();
        String pawnColor = board[row][column].getColor();
        return playerColor.equals(pawnColor);
    }

    public static boolean checkForWinner() {
        return true;
    }

}
