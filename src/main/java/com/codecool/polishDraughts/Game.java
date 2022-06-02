package com.codecool.polishDraughts;


public class Game {
    private static final int POSITION_A = 97;

    private Board gameBoard;
    private Player playerOne;
    private Player playerTwo;

    public Game(int size) {
        this.gameBoard = new Board(size);
        this.playerOne = new Player("black");
        this.playerTwo = new Player("white");

    }

    public void start() {
        Player winner = null;
        while (checkForWinner() == null) {
            playRound(playerOne);
            playRound(playerTwo);
        }

        this.gameBoard.toString();
        winner = checkForWinner();
        theWinner(winner);
    }

    public void playRound(Player player) {
        this.gameBoard.toString();
        Coordinate moveFrom = getMovePawnFrom(player);
        while (invalidPawnPick(player, moveFrom)) {
            System.out.println("Invalid Pawn pick! Try again!");
            moveFrom = getMovePawnFrom(player);
        }
        Coordinate moveTo = getMovePawnTo(player);
        while (invalidMove(player, moveFrom, moveTo)) {
            System.out.println("Invalid Pawn move! Try again!");
            moveTo = getMovePawnTo(player);
        }
        this.gameBoard.movePawn(moveFrom.getX(), moveFrom.getY(), moveTo.getX(), moveTo.getY());
    }

    private boolean invalidMove(Player player, Coordinate moveFrom, Coordinate moveTo) {
        return !isCoordinateOnBoard(moveTo) || !isFieldEmpty(moveTo.getX(), moveTo.getY()) ||
                !isValidDiagonalMove(player, moveFrom.getX(), moveFrom.getY(), moveTo.getX(), moveTo.getY());
    }

//    private boolean tryToMakeMove(Coordinate moveFrom, Coordinate moveTo) {
//        return false;
//    }

    private boolean isValidDiagonalMove(Player player, int moveFromX, int moveFromY, int moveToX, int moveToY) {
        return checkDiagonalMove(moveFromX, moveFromY, moveToX, moveToY) &&
                checkValidDiagMoveToOppnentWay(player, moveFromX, moveToX, moveFromY) &&
                isValidJump(moveFromX, moveFromY, moveToX, moveToY, player);
    }

    private boolean isValidJump(int moveFromX, int moveFromY, int moveToX, int moveToY, Player player) {
        if (Math.abs(moveFromX - moveToX) > 2) {
            return false;
        } else if (Math.abs(moveFromX - moveToX) == 1) {
            return true;

        } else {
            return this.gameBoard.isOppositePawnBetween(moveFromX, moveFromY, moveToX, moveToY, player);
        }
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
        try {
            rowFrom = player.getUserInputRow(player.getColor() + " Player: Enter row for Pawn to pick: ") - POSITION_A;
        } catch (Exception e) {

        }
        int column = player.getUserInputColumn(player.getColor() + " Player: Enter column for Pawn to pick: ");
        Coordinate result = new Coordinate(rowFrom, column);

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
        try {
            rowTo = player.getUserInputRow(player.getColor() + " Player: Enter row for place to move: ") - POSITION_A;
        } catch (Exception e) {

        }
        int columnTo = player.getUserInputColumn(player.getColor() + " Player: Enter column for place to move: ");
        Coordinate result = new Coordinate(rowTo, columnTo);
//        int[] result = new int[2];
//        int rowTo = (int)player.getUserInputRow("Row to move: " + player.getColor());
//        int columnTo = player.getUserInput("Column to move: " + player.getColor());
//
//        result[0] = rowTo;
//        result[1] = columnTo;

        return result;
    }


    public boolean checkDiagonalMove(int rowFrom, int columnFrom, int rowTo, int columnTo) {
        return (Math.abs(rowFrom - rowTo) - Math.abs(columnFrom - columnTo) == 0);
    }


    public Player checkForWinner() {
        return this.gameBoard.hasZeroPawn(playerOne, playerTwo);

    }

    public boolean checkValidDiagMoveToOppnentWay(Player player, int rowFrom, int rowTo, int columnFrom) {
        if (player.getColor().equals("black")) {
            if(this.gameBoard.checkPawnCrowned(rowFrom, columnFrom)) {
                return true;
            }
            return rowFrom < rowTo;
        } else {
            if(this.gameBoard.checkPawnCrowned(rowFrom, columnFrom)) {
                return true;
            }
            return rowTo < rowFrom;
        }

    }

    public static String theWinner(Player player) {

        return "The winner player is " + player.getColor();

    }
}
