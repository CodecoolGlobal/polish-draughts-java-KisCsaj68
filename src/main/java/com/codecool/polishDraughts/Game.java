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
        this.gameBoard.printBoard();
        winner = checkForWinner();
        theWinner(winner);
    }

    public void playRound(Player player) {
        boolean hasException = true;
        while (hasException) {
            try {
                this.gameBoard.printBoard();
                Coordinate moveFrom = getFromMove(player);
                while (invalidPawnPick(player, moveFrom)) {
                    System.err.println("Invalid Pawn pick! Try again!");
                    moveFrom = getFromMove(player);
                }
                Coordinate moveTo = getToMove(player);
                while (invalidMove(player, moveFrom, moveTo)) {
                    System.err.println("Invalid Pawn move! Try again!");
                    moveTo = getToMove(player);
                }
                this.gameBoard.movePawn(moveFrom.getX(), moveFrom.getY(), moveTo.getX(), moveTo.getY());
                hasException = false;
            } catch (InterruptPlayRound e) {
                // hasException remain true
            }
        }
    }

    private boolean invalidMove(Player player, Coordinate moveFrom, Coordinate moveTo) {
        return !isCoordinateOnBoard(moveTo) ||
                !isFieldEmpty(moveTo.getX(), moveTo.getY()) ||
                !isValidDiagonalMove(player, moveFrom.getX(), moveFrom.getY(), moveTo.getX(), moveTo.getY());
    }


    private boolean isValidDiagonalMove(Player player, int moveFromX, int moveFromY, int moveToX, int moveToY) {
        return checkDiagonalMove(moveFromX, moveFromY, moveToX, moveToY) &&
                checkValidBackwardsMove(player, moveFromX, moveToX, moveFromY) &&
                isValidJump(moveFromX, moveFromY, moveToX, moveToY, player);
    }

    private boolean isValidJump(int moveFromX, int moveFromY, int moveToX, int moveToY, Player player) {
        if (Math.abs(moveFromX - moveToX) > 2) {
            return false;
        } else if (Math.abs(moveFromX - moveToX) == 1) {
            return true;
        }
        return this.gameBoard.isOppositePawnBetween(moveFromX, moveFromY, moveToX, moveToY, player);
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

    public Coordinate getFromMove(Player player) throws InterruptPlayRound {
        return validateUserInput(player, " player, enter coordinate Pawn to pick: ");
    }

    public Coordinate getToMove(Player player) throws InterruptPlayRound {
        return validateUserInput(player, " player, coordinate field to move: ");
    }

    private Coordinate validateUserInput(Player player, String question) throws InterruptPlayRound {
        Coordinate userInput = null;
        try {
            String color = player.getColor();
            userInput = player.getUserInput("%s%s%s".formatted(color.substring(0, 1).toUpperCase(), color.substring(1), question));

            userInput.setX(userInput.getX() - POSITION_A);

        } catch (InvalidUserInputLength e) {
            userInput = new Coordinate(-1, -1);
        }
        return userInput;
    }

    public boolean checkDiagonalMove(int rowFrom, int columnFrom, int rowTo, int columnTo) {
        return (Math.abs(rowFrom - rowTo) - Math.abs(columnFrom - columnTo) == 0);
    }


    public Player checkForWinner() {
        return this.gameBoard.hasZeroPawn(playerOne, playerTwo);
    }

    public boolean checkValidBackwardsMove(Player player, int rowFrom, int rowTo, int columnFrom) {
        if (this.gameBoard.checkPawnCrowned(rowFrom, columnFrom)) {
            return true;
        }
        return player.getColor().equals("black") ? rowFrom < rowTo : rowTo < rowFrom;
    }

    public static String theWinner(Player player) {
        return "The winner player is %s".formatted(player.getColor());
    }
}
