package com.codecool.polishDraughts;


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
        int[] moveFrom = getMovePawnFrom(player);
        int[] moveTo = getMovePawnTo(player);

        while (!checkPlayerAndPawnColor(player, moveFrom[0], moveFrom[1]) || !checkToField(moveTo[0], moveTo[1]) || !checkDiagonalMove(moveFrom[0], moveFrom[1], moveTo[0], moveTo[1])) {
            if(!checkPlayerAndPawnColor(player, moveFrom[0], moveFrom[1])){
                moveFrom = getMovePawnFrom(player);
            } else if (!checkToField(moveTo[0], moveTo[1]) || !checkDiagonalMove(moveFrom[0], moveFrom[1], moveTo[0], moveTo[1])) {
                moveTo = getMovePawnTo(player);
            }
        }
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
        return checkPlayerAndPawnColor(player, rowFrom, columnFrom) || checkToField(rowTo, columnTo) || checkDiagonalMove(rowFrom, columnFrom, rowTo, columnTo);
    }

    public boolean checkPlayerAndPawnColor(Player player, int row, int column) {
        Pawn[][] board = this.gameBoard.getBoard();
        if (board[row][column] == null) {
            return false;
        }
        String playerColor = player.getColor();
        String pawnColor = board[row][column].getColor();

        return playerColor.equals(pawnColor);
    }

    public boolean checkToField(int row, int column) {
        Pawn[][] board = this.gameBoard.getBoard();
        if (board[row][column] == null) {
            return true;
        }
        return false;
    }

    public boolean checkDiagonalMove(int rowFrom, int columnFrom, int rowTo, int columnTo) {
        return (Math.abs(rowFrom-rowTo) - Math.abs(columnFrom-columnTo) == 0);
    }

    public static boolean checkForWinner() {
        return true;
    }

}
