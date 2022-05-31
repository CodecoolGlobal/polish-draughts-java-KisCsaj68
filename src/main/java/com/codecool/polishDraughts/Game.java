package com.codecool.polishDraughts;


public class Game {
    private static final int POSITION_A= 97;

    private Board gameBoard;
    private Player playerOne;
    private Player playerTwo;

    public Game(int size) {
        this.gameBoard = new Board(size);
        this.playerOne = new Player("black");
        this.playerTwo = new Player("white");

    }

    public void start() {
        playRound(playerOne);
        playRound(playerTwo);
        this.gameBoard.toString();
    }

    public void playRound(Player player) {
        this.gameBoard.toString();
        Coordinate moveFrom = getMovePawnFrom(player);
        while (invalidPawnPick(player, moveFrom)) {
                moveFrom = getMovePawnFrom(player);
            }
        Coordinate moveTo = getMovePawnTo(player);
        while  (invalidMove(moveFrom, moveTo)) {
                moveTo = getMovePawnTo(player);
            }

    }

    private boolean invalidMove(Coordinate moveFrom, Coordinate moveTo) {
        return !isCoordinateOnBoard(moveTo) || !isFieldEmpty(moveTo.getX(), moveTo.getY()) || !isValidDiagonalMove(moveFrom.getX(), moveFrom.getY(), moveTo.getX(), moveTo.getY());
    }

//    private boolean tryToMakeMove(Coordinate moveFrom, Coordinate moveTo) {
//        return false;
//    }

    private boolean isValidDiagonalMove(int moveFromX, int moveFromY, int moveToX, int moveToY) {
        return checkDiagonalMove(moveFromX, moveFromY, moveToX, moveToY);
    }

    private boolean isFieldEmpty(int moveToX, int moveToY) {
        return gameBoard.checkToField(moveToX, moveToY);
    }

    private boolean invalidPawnPick(Player player, Coordinate moveFrom) {
        return !isCoordinateOnBoard(moveFrom) || !isCorrectColorPicked(player, moveFrom.getX(), moveFrom.getY());
    }

    private boolean isCoordinateOnBoard(Coordinate moveFrom) {
        return gameBoard.checkCoordinateOnBoard(moveFrom.getX(), moveFrom.getY());
    }

    private boolean isCorrectColorPicked(Player player, int moveFromX, int moveFromY) {
        return gameBoard.checkPlayerAndPawnColor(player, moveFromX, moveFromY);
    }

    public Coordinate getMovePawnFrom(Player player) {
        int rowFrom = -1;
        try{
            rowFrom = player.getUserInputRow("Row from move: " + player.getColor()) - POSITION_A;
        }
        catch (Exception e) {

        }
        int column = player.getUserInputColumn("Column from move: " + player.getColor());
        Coordinate result = new Coordinate(rowFrom,column);

//        int row = (int) player.getUserInputRow("Row from move: " + player.getColor());
//        Coordinate result = new Coordinate(row, column);
//        Character rowFrom = player.getUserInputRow("Row from move: " + player.getColor());
//        int columnFrom = player.getUserInput("Column from move: " + player.getColor());
//
//        result[0] = rowFrom;
//        result[1] = columnFrom;

        return result;
    }

    public Coordinate getMovePawnTo(Player player) {
        int rowTo = -1;
        try{
            rowTo = player.getUserInputRow("Row to move: " + player.getColor()) - POSITION_A;
        }
        catch (Exception e) {

        }
        int columnTo = player.getUserInputColumn("Column to move: " + player.getColor());
        Coordinate result = new Coordinate(rowTo,columnTo);
//        int[] result = new int[2];
//        int rowTo = (int)player.getUserInputRow("Row to move: " + player.getColor());
//        int columnTo = player.getUserInput("Column to move: " + player.getColor());
//
//        result[0] = rowTo;
//        result[1] = columnTo;

        return result;
    }




    public boolean checkDiagonalMove(int rowFrom, int columnFrom, int rowTo, int columnTo) {
        return (Math.abs(rowFrom-rowTo) - Math.abs(columnFrom-columnTo) == 0);
    }

    public static boolean checkForWinner() {
        return true;
    }

}
